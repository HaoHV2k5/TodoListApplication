package com.vn.TodoList.service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vn.TodoList.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClearUserService {
    UserRepository userRepository;

    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void cleanActiveUser() {
        LocalDateTime localDateTime = LocalDateTime.now();
        userRepository.deleteAllByActiveFalseAndOtpExpireTimeBefore(localDateTime);
    }
}
