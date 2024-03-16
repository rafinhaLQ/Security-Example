package com.github.rafinhalq.securityexample.dto.user;

import com.github.rafinhalq.securityexample.repository.entity.Address;

import java.util.List;

public record UserDto(
    String id,

    String firstName,

    String lastName,

    String email,

    List<Address> addresses,

    String phoneNumber
) {}
