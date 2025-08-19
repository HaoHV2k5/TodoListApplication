package com.vn.TodoList.validator;

import jakarta.validation.ConstraintValidator;

public class UsernameValidator implements ConstraintValidator<UsernameConstraint, String> {

    @Override
    public void initialize(UsernameConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, jakarta.validation.ConstraintValidatorContext context) {
        if (username == null) {
            return true;
        }

        username = username.trim();

        if (username.isEmpty()) {
            return false;
        }
        
        return username.matches("^[A-Za-z][A-Za-z0-9_]{3,19}$");
    }
}