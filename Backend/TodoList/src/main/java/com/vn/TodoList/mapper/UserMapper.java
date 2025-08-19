package com.vn.TodoList.mapper;

import org.springframework.security.core.userdetails.UserDetails;

import com.vn.TodoList.dto.request.UserRequest;
import com.vn.TodoList.dto.response.AuthenticationResponse;
import com.vn.TodoList.entity.User;

public class UserMapper {
    public static AuthenticationResponse toResponse(User user, String token) {
        if (user == null) {
            return null;
        }
        AuthenticationResponse response = new AuthenticationResponse();
        response.setName(user.getName());
        response.setToken(token);
        return response;
    }

    public static User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return user;
    }

    public static UserDetails toUserDetails(User user) {
        if (user == null) {
            return null;
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
