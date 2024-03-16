package com.github.rafinhalq.securityexample.service.impl;

import com.github.rafinhalq.securityexample.repository.UserRepository;
import com.github.rafinhalq.securityexample.security.jwt.GenerateJwtToken;
import com.github.rafinhalq.securityexample.security.jwt.JwtTokenData;
import com.github.rafinhalq.securityexample.security.jwt.UserCredencial;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final GenerateJwtToken generateJWTToken;
    private final PasswordEncoder passwordEncoder;

    public JwtTokenData authenticateUser(UserCredencial userCredencial) {
        var optionalUser = userRepository.findByEmail(userCredencial.email());
        var user = optionalUser.orElseThrow(() -> new BadCredentialsException("No user registered with this credentials!"));

        if (!passwordEncoder.matches(userCredencial.password(), user.getPassword()))
            throw new BadCredentialsException("Invalid password");

        return generateJWTToken.generateToken(user.getId(), user.getEmail(), user.getAuthorities());
    }
}
