package com.github.rafinhalq.securityexample.security.jwt.filter;

import com.github.rafinhalq.securityexample.security.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var jwt = request.getHeader(AUTHORIZATION_HEADER);
        var path = request.getServletPath();
        if (hasText(jwt)) {
            try {
                jwt = checkToken(jwt);

                SecretKey secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

                Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();

                checkUserId(path, claims.getSubject());

                var email = (String) claims.get("email");
                var authorities = (String) claims.get("authorities");

                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid Token");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String checkToken(String jwt) {
        if (!jwt.startsWith("Bearer "))
            throw new BadCredentialsException("Invalid Token");

        return jwt.replace("Bearer ", "");
    }

    private void checkUserId(String path, String userId) {
        if (!path.contains("/user/" + userId)) {
            throw new BadCredentialsException("User ID doesn't match");
        }
    }
}
