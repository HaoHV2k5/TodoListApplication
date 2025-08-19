package com.vn.TodoList.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.vn.TodoList.config.JwtUtils;
import com.vn.TodoList.dto.request.AuthenticationRequest;
import com.vn.TodoList.dto.response.AuthenticationResponse;
import com.vn.TodoList.entity.User;
import com.vn.TodoList.exception.AppException;
import com.vn.TodoList.mapper.AuthenticationMapper;
import com.vn.TodoList.mapper.UserMapper;
import com.vn.TodoList.model.ErrorCode;
import com.vn.TodoList.repository.UserRepository;
import com.vn.TodoList.utils.PasswordUtil;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils = new JwtUtils();

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (!userRepository.existsByUsername(request.getUsername())) {
            Logger.getLogger(this.getClass().getName()).warning("Username is not available: " + request.getUsername());
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        AuthenticationResponse reponse;
        
        User user = userRepository.findById(request.getUsername()).get();

        if (!PasswordUtil.verifyPassword(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        String token = jwtUtils.generateToken(UserMapper.toUserDetails(user));

        reponse = AuthenticationMapper.toResponse(user, token);

        return reponse;
    }
}
