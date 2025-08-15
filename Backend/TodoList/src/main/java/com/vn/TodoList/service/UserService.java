package com.vn.TodoList.service;

import org.springframework.stereotype.Service;

import com.vn.TodoList.dto.request.UserRequest;
import com.vn.TodoList.dto.response.UserResponse;
import com.vn.TodoList.entity.User;
import com.vn.TodoList.exception.AppException;
import com.vn.TodoList.mapper.UserMapper;
import com.vn.TodoList.model.ErrorCode;
import com.vn.TodoList.repository.UserRepository;
import com.vn.TodoList.utils.PasswordUtil;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse registerUser(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        User user;

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new AppException(ErrorCode.DUPLICATE_USERNAME);
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new AppException(ErrorCode.DUPLICATE_EMAIL);
        }

        user = UserMapper.toEntity(userRequest);
        
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        userRepository.save(user);

        userResponse = UserMapper.toResponse(user);

        return userResponse;
    }

    public UserResponse updateEmail(String username, UserRequest request) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)); // must change by JWT
        UserResponse userResponse = new UserResponse();

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.DUPLICATE_EMAIL);
        }

        user = UserMapper.toEntity(request);

        userRepository.save(user);

        userResponse = UserMapper.toResponse(user);

        return userResponse;
    }

    public UserResponse updatePassword(String username, UserRequest request) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)); // must change by JWT
        UserResponse userResponse = new UserResponse();

        user = UserMapper.toEntity(request);

        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        userRepository.save(user);

        userResponse = UserMapper.toResponse(user);

        return userResponse;
    }

    // public User loginUser(String username, String password);

    // public boolean deleteUser(String username);

}
