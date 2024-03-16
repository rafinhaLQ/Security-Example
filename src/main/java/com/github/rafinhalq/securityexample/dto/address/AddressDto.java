package com.github.rafinhalq.securityexample.dto.address;

public record AddressDto(
    boolean isMain,

    String cep,

    String addressLine1,

    String addressLine2,

    String district,

    String city,

    String state
) {}
