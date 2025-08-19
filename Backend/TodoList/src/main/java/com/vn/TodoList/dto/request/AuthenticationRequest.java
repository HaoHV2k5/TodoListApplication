package com.vn.TodoList.dto.request;

import com.vn.TodoList.validator.PasswordConstraint;
import com.vn.TodoList.validator.UsernameConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    @UsernameConstraint
    private String username;

    @PasswordConstraint
    private String password;
}
