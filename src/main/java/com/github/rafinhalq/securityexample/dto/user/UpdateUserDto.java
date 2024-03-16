package com.github.rafinhalq.securityexample.dto.user;

import com.github.rafinhalq.securityexample.dto.address.CreateAddressDto;
import com.github.rafinhalq.securityexample.validation.AllAttributesNotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;

@AllAttributesNotNull(isRecord = true)
public record UpdateUserDto(
    @NotBlank(message = "First Name must not be blank")
    String firstName,

    @NotBlank(message = "Last Name must not be blank")
    String lastName,

    @Email(message = "Invalid Email")
    String email,

    @NotNull(message = "Address must not be null")
    @Validated
    CreateAddressDto addressDto,

    @Pattern(regexp = "([(]?\\d{2}[)]?)\\d{5}-?\\d{4}", message = "Invalid Phone Number")
    String phoneNumber
) {}
