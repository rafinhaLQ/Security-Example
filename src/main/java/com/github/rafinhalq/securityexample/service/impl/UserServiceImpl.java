package com.github.rafinhalq.securityexample.service.impl;

import com.github.rafinhalq.securityexample.dto.address.CreateAddressDto;
import com.github.rafinhalq.securityexample.dto.user.CreateUserDto;
import com.github.rafinhalq.securityexample.dto.user.UpdateUserDto;
import com.github.rafinhalq.securityexample.dto.user.UpdateUserPasswordDto;
import com.github.rafinhalq.securityexample.dto.user.UserDto;
import com.github.rafinhalq.securityexample.exception.GenericException;
import com.github.rafinhalq.securityexample.repository.UserRepository;
import com.github.rafinhalq.securityexample.repository.entity.Authority;
import com.github.rafinhalq.securityexample.repository.entity.UserEntity;
import com.github.rafinhalq.securityexample.service.AddressService;
import com.github.rafinhalq.securityexample.service.UserService;
import com.github.rafinhalq.securityexample.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AddressService addressService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String createUser(CreateUserDto createUserDto) {
        var createAddress = new CreateAddressDto(createUserDto.cep(), true);
        var address = addressService.createAddress(createAddress);

        var userEntity = userMapper.createUserToEntity(createUserDto, List.of(address));

        var hashPwd = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(hashPwd);

        var basicAuthority = new Authority("ROLE_USER");
        userEntity.setAuthorities(Set.of(basicAuthority));

        var savedUser = userRepository.save(userEntity);

        return savedUser.getId();
    }

    @Override
    public UserDto getUser(String id) {
        var user = getUserById(id);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUser(String id, UpdateUserDto updateUserDto) {
        var currentUserInfo = getUserById(id);

        var user = userMapper.updateUserToEntity(updateUserDto, currentUserInfo);

        var addresses = addressService.addAddress(user.getAddresses(), updateUserDto.addressDto());
        user.setAddresses(addresses);

        var savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public void updateUserPassword(String id, UpdateUserPasswordDto password) {
        var currentUserInfo = getUserById(id);

        if (!passwordEncoder.matches(password.oldPassword(), currentUserInfo.getPassword()))
            throw new BadCredentialsException("Invalid password");

        var hashPwd = passwordEncoder.encode(password.newPassword());

        currentUserInfo.setPassword(hashPwd);

        userRepository.save(currentUserInfo);
    }

    @Override
    public void updateUserRoles(String id, List<String> roles) {
        var currentUserInfo = getUserById(id);

        roles.stream()
            .map(Authority::new)
            .forEach(currentUserInfo.getAuthorities()::add);

        userRepository.save(currentUserInfo);
    }

    @Override
    public void deleteUser(String id) {
        var user = getUserById(id);
        userRepository.delete(user);
    }

    private UserEntity getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new GenericException("User not found", HttpStatus.NOT_FOUND));
    }

}
