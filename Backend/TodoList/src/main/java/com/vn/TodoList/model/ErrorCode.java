package com.vn.TodoList.model;

public enum ErrorCode {
    DUPLICATE_USERNAME(1, "Username already exists"),
    DUPLICATE_EMAIL(2, "Email already exists"),
    USER_NOT_FOUND(3, "User not found"),
    MISSING_USERNAME(4, "Username is required"),
    MISSING_PASSWORD(5, "Password is required"),
    MISSING_EMAIL(6, "Email is required"),
    INVALID_CREDENTIALS(7, "Invalid username or password"),
    INVALID_TOKEN(8, "Invalid token"),
    TOKEN_EXPIRED(9, "Token has expired"),
    UNAUTHORIZED(10, "Unauthorized access"),
    MISSING_TOKEN(11, "Token is required"),
    INTERNAL_VALIDATE_KEY_ERROR(998, "Internal server error"),
    INTERNAL_SERVER_ERROR(999, "Internal server error");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    // /**
    //  * This method retrieves the ErrorCode based on the provided code.
    //  * If the code does not match any predefined error codes, it returns
    //  * INTERNAL_SERVER_ERROR as a default.
    //  * @param code the error code to look up
    //  * @return the message associated with the error code
    //  */
    // public static String getMessage(int code) {
    //     for (ErrorCode errorCode : values()) {
    //         if (errorCode.getCode() == code) {
    //             return errorCode.getMessage();
    //         }
    //     }
    //     return INTERNAL_SERVER_ERROR.getMessage(); // Default to internal server error if code not found
    // }
}
