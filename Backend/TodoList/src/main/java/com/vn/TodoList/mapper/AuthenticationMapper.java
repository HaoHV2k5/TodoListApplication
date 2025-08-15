package com.vn.TodoList.mapper;

import com.vn.TodoList.dto.response.AuthenticationResponse;
import com.vn.TodoList.entity.User;

public class AuthenticationMapper {
    public static AuthenticationResponse toResponse(User user, String token) {
        return new AuthenticationResponse(token, user.getUsername(), user.getPassword());
    }
}
