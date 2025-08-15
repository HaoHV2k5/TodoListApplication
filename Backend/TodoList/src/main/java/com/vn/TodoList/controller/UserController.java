package com.vn.TodoList.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.TodoList.dto.request.UserRequest;
import com.vn.TodoList.dto.response.ApiResponse;
import com.vn.TodoList.dto.response.UserResponse;
import com.vn.TodoList.exception.AppException;
import com.vn.TodoList.model.ErrorCode;
import com.vn.TodoList.service.UserService;
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
    public ApiResponse<UserResponse> registerUser(@RequestBody UserRequest userRequest) {        
        UserResponse user = userService.registerUser(userRequest);
        return new ApiResponse<>("User registered successfully", user);
    }

    // FIX
    @PutMapping("/updateEmail")
    public ApiResponse<UserResponse> updateEmail(@RequestBody UserRequest userRequest, @RequestBody String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_USERNAME); // Handle missing username
        }

        UserResponse user = userService.updateEmail(username, userRequest);

        return new ApiResponse<>("User updated email successfully", user);
    }
    
    // FIX
    @PutMapping("/updatePassword")
    public ApiResponse<UserResponse> updatePassword(@RequestBody UserRequest userRequest, @RequestBody String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new AppException(ErrorCode.MISSING_USERNAME); // Handle missing username
        }

        UserResponse user = userService.updatePassword(username, userRequest);

        return new ApiResponse<>("User updated password successfully", user);
    }
    
    // public ResponseEntity<User> loginUser(@RequestParam String username, @RequestParam String password) {
    //          return ResponseEntity.ok(userService.loginUser(username, password));
    //      }

}
