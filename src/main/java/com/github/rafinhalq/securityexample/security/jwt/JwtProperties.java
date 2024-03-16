package com.github.rafinhalq.securityexample.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey;
    private long expiration;
}
