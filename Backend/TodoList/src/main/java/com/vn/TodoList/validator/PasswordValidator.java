package com.vn.TodoList.validator;

public class PasswordValidator implements jakarta.validation.ConstraintValidator<PasswordConstraint, String> {

    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, jakarta.validation.ConstraintValidatorContext context) {
        if (password == null) {
            return true;
        }

        password = password.trim();

        if (password.isEmpty()) {
            return false;
        }

        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,20}$"); // TODO - Update regex as per requirements
    }
}