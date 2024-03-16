package com.github.rafinhalq.securityexample.service.impl;

import com.github.rafinhalq.securityexample.client.ViaCepClient;
import com.github.rafinhalq.securityexample.dto.address.CreateAddressDto;
import com.github.rafinhalq.securityexample.repository.entity.Address;
import com.github.rafinhalq.securityexample.service.AddressService;
import com.github.rafinhalq.securityexample.service.mapper.ViaCepResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ViaCepClient viaCepClient;

    private final ViaCepResponseMapper viaCepResponseMapper;

    @Override
    public Address createAddress(CreateAddressDto createAddressDto) {
        var viaCepResponse = viaCepClient.getAddressByCep(createAddressDto.cep());

        var address = viaCepResponseMapper.toAddressDto(viaCepResponse);
        address.setMain(createAddressDto.isMain());

        return address;
    }

    @Override
    public List<Address> addAddress(List<Address> currentAddresses, CreateAddressDto addressDto) {
        if (addressDto.isMain()) {
            currentAddresses.stream()
                .filter(Address::isMain)
                .forEach(address -> address.setMain(false));
        }
        currentAddresses.add(createAddress(addressDto));
        return currentAddresses;
    }

}
