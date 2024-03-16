package com.github.rafinhalq.securityexample.security.jwt;

import com.github.rafinhalq.securityexample.repository.entity.Authority;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class GenerateJwtToken {
    private final JwtProperties jwtProperties;

    public JwtTokenData generateToken(String userId, String email, Set<Authority> authorities) {
        var issuedAt = new Date();
        var expirationAt = new Date(issuedAt.getTime() + jwtProperties.getExpiration());

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
            .issuer("Let Go API")
            .subject(userId)
            .claim("authorities", populateAuthorities(authorities))
            .claim("email", email)
            .issuedAt(issuedAt)
            .expiration(expirationAt)
            .signWith(secretKey)
            .compact();

        return new JwtTokenData(jwt);
    }

    private String populateAuthorities(Set<Authority> authorities) {
        return authorities.stream()
            .map(Authority::name)
            .collect(Collectors.joining(","));
    }

}
