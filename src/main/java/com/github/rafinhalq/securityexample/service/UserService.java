package com.github.rafinhalq.securityexample.service;

import com.github.rafinhalq.securityexample.dto.user.CreateUserDto;
import com.github.rafinhalq.securityexample.dto.user.UpdateUserDto;
import com.github.rafinhalq.securityexample.dto.user.UpdateUserPasswordDto;
import com.github.rafinhalq.securityexample.dto.user.UserDto;

import java.util.List;

public interface UserService {
    String createUser(CreateUserDto createUserDto);

    UserDto getUser(String id);

    UserDto updateUser(String id, UpdateUserDto updateUserDto);

    void updateUserPassword(String id, UpdateUserPasswordDto password);

    void updateUserRoles(String id, List<String> roles);

    void deleteUser(String id);
}
