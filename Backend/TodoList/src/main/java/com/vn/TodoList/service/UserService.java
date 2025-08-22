package com.vn.TodoList.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.vn.TodoList.config.OTPUtils;
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
    private final EmailService emailService;
    private final int timeExpireOTP = 5;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public void registerUser(UserRequest userRequest) {
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

        // generate otp
        String OTP = OTPUtils.generateOTP();
        user.setOtp(OTP);
        user.setOtpExpireTime(LocalDateTime.now().plusMinutes(timeExpireOTP));
        user.setActive(false);

        userRepository.save(user);

        userResponse = UserMapper.toResponse(user);

        // send email
        emailService.sendEmailOTP(user.getEmail(), OTP);

    }

    public boolean verifyOTP(String email, String otp) {
        boolean result = false;
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (user.getOtp().equals(otp) && user.getOtpExpireTime().isAfter(LocalDateTime.now())) {
            user.setActive(true);
            user.setOtp(null);
            user.setOtpExpireTime(null);
            userRepository.save(user);
            result = true;
        }
        return result;
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
