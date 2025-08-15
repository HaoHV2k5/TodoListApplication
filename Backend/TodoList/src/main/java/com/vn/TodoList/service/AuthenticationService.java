package com.vn.TodoList.service;

import org.springframework.stereotype.Service;

import com.vn.TodoList.dto.request.AuthenticationRequest;
import com.vn.TodoList.dto.response.AuthenticationResponse;
import com.vn.TodoList.entity.User;
import com.vn.TodoList.exception.AppException;
import com.vn.TodoList.mapper.AuthenticationMapper;
import com.vn.TodoList.model.ErrorCode;
import com.vn.TodoList.repository.UserRepository;
import com.vn.TodoList.utils.PasswordUtil;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    public AuthenticationResponse authenticateByUsernameAndPassword(AuthenticationRequest request) {
        if (!isUsernameAvailable(request.getUsername())) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        AuthenticationResponse reponse;
        
        User user = userRepository.findById(request.getUsername()).get();

        if (!PasswordUtil.verifyPassword(request.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        // TODO - something to get token

        reponse = AuthenticationMapper.toResponse(user, "token_placeholder");

        return reponse;
    }

    public AuthenticationResponse authenticateByToken(AuthenticationRequest request) {
        AuthenticationResponse reponse;
        
        User user = userRepository.findById(request.getUsername()).get(); // TODO - chuyen sang dung token lay nguoi dung

        // TODO - something to get token

        reponse = AuthenticationMapper.toResponse(user, "token_placeholder");

        return reponse;
    }
}
