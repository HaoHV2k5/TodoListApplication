package com.vn.TodoList.config;

import java.security.SecureRandom;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OTPUtils {
    static SecureRandom random = new SecureRandom();

    public static String generateOTP() {
        int number = 100000 + random.nextInt(9000000); // 6 so otp
        return String.valueOf(number);
    }
}
