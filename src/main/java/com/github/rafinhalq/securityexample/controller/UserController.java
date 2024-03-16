package com.github.rafinhalq.securityexample.controller;

import com.github.rafinhalq.securityexample.dto.user.CreateUserDto;
import com.github.rafinhalq.securityexample.dto.user.UpdateUserDto;
import com.github.rafinhalq.securityexample.dto.user.UpdateUserPasswordDto;
import com.github.rafinhalq.securityexample.dto.user.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "User",  description = "User API")
public interface UserController {
    @Operation(summary = "Register user", description = "Register user and return user id")
    ResponseEntity<String> registerUser(CreateUserDto createUserDto);

    @Operation(summary = "Get user info", description = "Get user info by user id",
        security = @SecurityRequirement(name = "JWT"))
    ResponseEntity<UserDto> getUserInfo(String userId);

    @Operation(summary = "Update user info", description = "Update user info by user id",
        security = @SecurityRequirement(name = "JWT"))
    ResponseEntity<UserDto> updateUser(String userId, UpdateUserDto updateUserDto);

    @Operation(summary = "Update user password", description = "Update user password by user id",
        security = @SecurityRequirement(name = "JWT"))
    ResponseEntity<String> updateUserPassword(String userId, UpdateUserPasswordDto password);

    @Operation(summary = "Update user roles", description = "Update user roles by user id",
        security = @SecurityRequirement(name = "JWT"))
    ResponseEntity<String> updateUserRoles(String userId, List<String> authorities);

    @Operation(summary = "Delete user", description = "Delete user by user id",
        security = @SecurityRequirement(name = "JWT"))
    ResponseEntity<String> deleteUser(String userId);
}
