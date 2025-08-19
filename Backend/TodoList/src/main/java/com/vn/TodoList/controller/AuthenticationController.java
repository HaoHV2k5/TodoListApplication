package com.vn.TodoList.controller;

import org.springframework.web.bind.annotation.RestController;

import com.vn.TodoList.dto.request.AuthenticationRequest;
import com.vn.TodoList.dto.response.ApiResponse;
import com.vn.TodoList.dto.response.AuthenticationResponse;
import com.vn.TodoList.exception.AppException;
import com.vn.TodoList.model.ErrorCode;
import com.vn.TodoList.service.AuthenticationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        if (request.getUsername() == null) {
            throw new AppException(ErrorCode.MISSING_USERNAME);
        }
        if (request.getPassword() == null) {
            throw new AppException(ErrorCode.MISSING_PASSWORD);
        }

        AuthenticationResponse response = authenticationService.authenticate(request);

        return new ApiResponse<>("Login successful", response);
    }

    // TODO - se doi thanh refresh token sau nay
    // @PostMapping("/check-login")
    // public ApiResponse<AuthenticationResponse> checkLogin(@RequestBody AuthenticationRequest request) {
    //     if (request.getToken() == null || request.getToken().trim().isEmpty()) {
    //         throw new AppException(ErrorCode.MISSING_TOKEN);
    //     }

    //     AuthenticationResponse response = authenticationService.authenticateByToken(request);

    //     return new ApiResponse<>("Login successful", response);
    // }
    
}
