package com.github.rafinhalq.securityexample.controller;

import com.github.rafinhalq.securityexample.security.jwt.JwtTokenData;
import com.github.rafinhalq.securityexample.security.jwt.UserCredencial;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication",  description = "Authentication API")
public interface AuthenticationController {
    @Operation(summary = "Authenticate user",  description = "Authenticate user and return JWT token")
    ResponseEntity<JwtTokenData> authenticateUser(UserCredencial userCredencial);
}
