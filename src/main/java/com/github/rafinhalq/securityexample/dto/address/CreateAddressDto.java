package com.github.rafinhalq.securityexample.dto.address;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateAddressDto(
    @NotNull
    @Pattern(regexp = "\\d{5}-?\\d{3}", message = "Invalid CEP")
    String cep,

    boolean isMain
) {}
