package com.vn.TodoList.service;

import org.springframework.stereotype.Service;

import com.vn.TodoList.config.JwtUtils;
import com.vn.TodoList.dto.request.UserRequest;
import com.vn.TodoList.dto.response.AuthenticationResponse;
import com.vn.TodoList.entity.User;
import com.vn.TodoList.exception.AppException;
import com.vn.TodoList.mapper.UserMapper;
import com.vn.TodoList.model.ErrorCode;
import com.vn.TodoList.repository.UserRepository;
import com.vn.TodoList.utils.PasswordUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils = new JwtUtils();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationResponse registerUser(UserRequest userRequest) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        User user;

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new AppException(ErrorCode.DUPLICATE_USERNAME);
        }

        user = UserMapper.toEntity(userRequest);
        
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        user.setName(user.getUsername()); // default name is username, can be changed later

        userRepository.save(user);

        String token = jwtUtils.generateToken(UserMapper.toUserDetails(user));

        authenticationResponse = UserMapper.toResponse(user, token);

        return authenticationResponse;
    }

    public AuthenticationResponse updateEmail(String username, UserRequest request) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.DUPLICATE_EMAIL);
        }

        user = userRepository.findByUsername(username);

        user.setEmail(request.getEmail());

        // TODO - Xac thuc OTP

        userRepository.save(user);

        return null;
    }

    public AuthenticationResponse updatePassword(String username, UserRequest request) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        user = userRepository.findByUsername(username);

        user.setPassword(request.getPassword());

        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));

        // TODO - Xac thuc OTP

        userRepository.save(user);

        return null;
    }

    public AuthenticationResponse updateName(String username, UserRequest request) {
        User user = userRepository.findById(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        user = userRepository.findByUsername(username);

        user.setName(request.getName());

        userRepository.save(user);

        return null;
    }

    public String getUsernameFromToken(String token) {
        return jwtUtils.getUsernameFromToken(token);
    }

}
