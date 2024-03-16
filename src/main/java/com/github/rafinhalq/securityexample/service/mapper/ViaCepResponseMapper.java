package com.github.rafinhalq.securityexample.service.mapper;

import com.github.rafinhalq.securityexample.client.model.ViaCepResponse;
import com.github.rafinhalq.securityexample.repository.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ViaCepResponseMapper {
    @Mapping(target = "main", ignore = true)
    @Mapping(target = "addressLine1", source = "logradouro")
    @Mapping(target = "addressLine2", source = "complemento")
    @Mapping(target = "district", source = "bairro")
    @Mapping(target = "city", source = "localidade")
    @Mapping(target = "state", source = "uf")
    Address toAddressDto(ViaCepResponse viaCepResponse);
}
