package com.github.rafinhalq.securityexample.security.jwt;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCredencial(
    @Email
    String email,

    @NotBlank
    String password

) {}
