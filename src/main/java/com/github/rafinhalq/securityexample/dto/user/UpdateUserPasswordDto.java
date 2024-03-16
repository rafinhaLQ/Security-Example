package com.github.rafinhalq.securityexample.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserPasswordDto(
    @NotBlank(message = "Old password is required")
    String oldPassword,
    @NotBlank(message = "New password is required")
    String newPassword) {
}
