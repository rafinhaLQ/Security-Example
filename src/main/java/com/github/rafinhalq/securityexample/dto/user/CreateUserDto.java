package com.github.rafinhalq.securityexample.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserDto(
    @NotBlank(message = "First name must not be blank")
    String firstName,

    @NotBlank(message = "Last name must not be blank")
    String lastName,

    @Email(message = "Invalid email")
    String email,

    @NotBlank(message = "Password must not be blank")
    String password,

    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "Invalid CEP")
    String cep,

    @Pattern(regexp = "([(]?\\d{2}[)]?)\\d{5}-?\\d{4}", message = "Invalid Phone Number")
    String phoneNumber
) {}
