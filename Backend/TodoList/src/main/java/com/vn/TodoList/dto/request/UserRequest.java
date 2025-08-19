package com.vn.TodoList.dto.request;

import com.vn.TodoList.validator.PasswordConstraint;
import com.vn.TodoList.validator.UsernameConstraint;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserRequest {
    @UsernameConstraint
    private String username;

    @PasswordConstraint
    private String password;
    
    private String confirmPassword;

    @Email(message = "INVALID_EMAIL")
    private String email;

    // @NotBlank(message = "INVALID_NAME")
    private String name;
}
