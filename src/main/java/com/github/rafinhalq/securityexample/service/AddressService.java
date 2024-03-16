package com.github.rafinhalq.securityexample.service;

import com.github.rafinhalq.securityexample.dto.address.CreateAddressDto;
import com.github.rafinhalq.securityexample.repository.entity.Address;

import java.util.List;

public interface AddressService {
    Address createAddress(CreateAddressDto createAddressDto);
    List<Address> addAddress(List<Address> currentAddresses, CreateAddressDto addressDto);
}
