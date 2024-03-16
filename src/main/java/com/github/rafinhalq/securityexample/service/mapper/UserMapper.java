package com.github.rafinhalq.securityexample.service.mapper;

import com.github.rafinhalq.securityexample.dto.user.CreateUserDto;
import com.github.rafinhalq.securityexample.dto.user.UpdateUserDto;
import com.github.rafinhalq.securityexample.dto.user.UserDto;
import com.github.rafinhalq.securityexample.repository.entity.Address;
import com.github.rafinhalq.securityexample.repository.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AddressMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserDto toDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    UserEntity createUserToEntity(CreateUserDto createUserDto, List<Address> addresses);

    @Mapping(target = "firstName",
        expression = "java(userDto.firstName() != null ? userDto.firstName() : currentUserInfo.getFirstName())")
    @Mapping(target = "lastName",
        expression = "java(userDto.lastName() != null ? userDto.lastName() : currentUserInfo.getLastName())")
    @Mapping(target = "email",
        expression = "java(userDto.email() != null ? userDto.email() : currentUserInfo.getEmail())")
    @Mapping(target = "phoneNumber",
        expression = "java(userDto.phoneNumber() != null ? userDto.phoneNumber() : currentUserInfo.getPhoneNumber())")
    UserEntity updateUserToEntity(UpdateUserDto userDto, UserEntity currentUserInfo);
}
