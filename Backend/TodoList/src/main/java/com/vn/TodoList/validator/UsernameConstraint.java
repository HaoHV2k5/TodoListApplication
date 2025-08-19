package com.vn.TodoList.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UsernameValidator.class})
public @interface UsernameConstraint {
    String message() default "INVALID_USERNAME";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
