package com.vn.TodoList.mapper;

import com.vn.TodoList.dto.request.UserRequest;
import com.vn.TodoList.dto.response.UserResponse;
import com.vn.TodoList.entity.User;

public class UserMapper {
    public static UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return response;
    }

    public static User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        return user;
    }
    
}
