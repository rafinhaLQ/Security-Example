package com.github.rafinhalq.securityexample.security.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;

public record JwtTokenData(
    @JsonProperty("jwt_token")
    String jwtToken
) {}
