package com.github.rafinhalq.securityexample.security.configuration;

import com.github.rafinhalq.securityexample.security.jwt.filter.JwtTokenValidatorFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private static final String[] DOCUMENTATION_OPENAPI = {
        "/swagger-ui/**",
        "docs.html",
        "docs-json/**"
    };

    private final JwtTokenValidatorFilter jwtTokenValidatorFilter;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .addFilterBefore(jwtTokenValidatorFilter, BasicAuthenticationFilter.class)
            .authorizeHttpRequests(requests -> requests
                .requestMatchers(DOCUMENTATION_OPENAPI).permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/user/register", "/user/authenticate").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
