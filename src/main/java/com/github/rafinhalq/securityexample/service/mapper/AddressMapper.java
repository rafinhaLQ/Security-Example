package com.github.rafinhalq.securityexample.service.mapper;

import com.github.rafinhalq.securityexample.dto.address.AddressDto;
import com.github.rafinhalq.securityexample.repository.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "main", source = "isMain")
    Address toEntity(AddressDto addressDto);

    @Mapping(target = "isMain", source = "main")
    AddressDto toDto(Address address);
}
