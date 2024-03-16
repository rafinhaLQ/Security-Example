package com.github.rafinhalq.securityexample.controller.impl;

import com.github.rafinhalq.securityexample.controller.AuthenticationController;
import com.github.rafinhalq.securityexample.security.jwt.JwtTokenData;
import com.github.rafinhalq.securityexample.security.jwt.UserCredencial;
import com.github.rafinhalq.securityexample.service.impl.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationService authenticationService;

    @Override
    @PostMapping(path = "/user/authenticate")
    public ResponseEntity<JwtTokenData> authenticateUser(@Valid @RequestBody UserCredencial userCredencial) {
        return ResponseEntity.ok(
            authenticationService.authenticateUser(userCredencial));
    }
}
