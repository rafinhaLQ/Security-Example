package com.github.rafinhalq.securityexample.controller.impl;

import com.github.rafinhalq.securityexample.controller.UserController;
import com.github.rafinhalq.securityexample.dto.user.CreateUserDto;
import com.github.rafinhalq.securityexample.dto.user.UpdateUserDto;
import com.github.rafinhalq.securityexample.dto.user.UpdateUserPasswordDto;
import com.github.rafinhalq.securityexample.dto.user.UserDto;
import com.github.rafinhalq.securityexample.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    @PostMapping(path = "/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(String.format("User %s created!", userId));
    }

    @Override
    @GetMapping(path = "/{user_id}")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable(value = "user_id") String userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @Override
    @PatchMapping(path = "/{user_id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "user_id") String userId,
            @Valid @RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userService.updateUser(userId, updateUserDto));
    }

    @Override
    @PatchMapping(path = "/{user_id}/password")
    public ResponseEntity<String> updateUserPassword(@PathVariable(value = "user_id") String userId,
            @Valid @RequestBody UpdateUserPasswordDto password) {
        userService.updateUserPassword(userId, password);
        return ResponseEntity.ok("User password updated!");
    }

    @Override
    @PatchMapping(path = "/{user_id}/roles")
    public ResponseEntity<String> updateUserRoles(@PathVariable(value = "user_id") String userId,
            @Valid @RequestBody List<@NotBlank String> authorities) {
        userService.updateUserRoles(userId, authorities);
        return ResponseEntity.ok("User authorities updated!");
    }

    @Override
    @DeleteMapping(path = "/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "user_id") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User Deleted");
    }

}
