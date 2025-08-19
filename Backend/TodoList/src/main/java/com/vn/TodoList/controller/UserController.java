package com.vn.TodoList.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.TodoList.config.JwtUtils;
import com.vn.TodoList.dto.request.UserRequest;
import com.vn.TodoList.dto.response.ApiResponse;
import com.vn.TodoList.dto.response.AuthenticationResponse;
import com.vn.TodoList.exception.AppException;
import com.vn.TodoList.model.ErrorCode;
import com.vn.TodoList.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse<AuthenticationResponse> registerUser(@RequestBody @Valid UserRequest userRequest) {
        if(userRequest.getUsername() == null) {
            throw new AppException(ErrorCode.MISSING_USERNAME);
        }

        if(userRequest.getPassword() == null) {
            throw new AppException(ErrorCode.MISSING_PASSWORD);
        }

        if (userRequest.getConfirmPassword() == null || 
            !userRequest.getPassword().trim().equals(userRequest.getConfirmPassword().trim())) {
            throw new AppException(ErrorCode.PASSWORD_MISMATCH);
        }

        AuthenticationResponse user = userService.registerUser(userRequest);
        return new ApiResponse<>("User registered successfully", user);
    }

    @PutMapping("/updateEmail")
    public ApiResponse<AuthenticationResponse> updateEmail(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UserRequest userRequest) {
        String token = JwtUtils.getTokenFromHeader(authorizationHeader);
        if (token == null || token.isEmpty()) {
            throw new AppException(ErrorCode.MISSING_TOKEN);
        }

        if(userRequest.getUsername() == null) {
            throw new AppException(ErrorCode.INVALID_USERNAME);
        }

        String username = userService.getUsernameFromToken(token);

        AuthenticationResponse user = userService.updateEmail(username, userRequest);

        return new ApiResponse<>("User updated email successfully", user);
    }
    
    @PutMapping("/updatePassword")
    public ApiResponse<AuthenticationResponse> updatePassword(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UserRequest userRequest) {
        String token = JwtUtils.getTokenFromHeader(authorizationHeader);
        if (token == null || token.isEmpty()) {
            throw new AppException(ErrorCode.MISSING_TOKEN);
        }

        if(userRequest.getPassword() == null) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        String username = userService.getUsernameFromToken(token);

        AuthenticationResponse user = userService.updatePassword(username, userRequest);

        return new ApiResponse<>("User updated password successfully", user);
    }

    @PutMapping("/updateName")
    public ApiResponse<AuthenticationResponse> updateName(@RequestHeader("Authorization") String authorizationHeader, @RequestBody UserRequest userRequest) {
        String token = JwtUtils.getTokenFromHeader(authorizationHeader);
        if (token == null || token.isEmpty()) {
            throw new AppException(ErrorCode.MISSING_TOKEN);
        }

        if(userRequest.getName() == null) {
            throw new AppException(ErrorCode.INVALID_NAME);
        }

        String username = userService.getUsernameFromToken(token);

        AuthenticationResponse user = userService.updateName(username, userRequest);

        return new ApiResponse<>("User updated password successfully", user);
    }
}
