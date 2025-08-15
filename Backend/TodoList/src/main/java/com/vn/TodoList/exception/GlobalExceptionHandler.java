package com.vn.TodoList.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vn.TodoList.dto.response.ApiResponse;
import com.vn.TodoList.model.ErrorCode;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(Exception e) {
        // TODO: Neu xay ra trong day tuc la co ham chua handle exception

        return handleAppException(new AppException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Object> response = new ApiResponse<>();

        response.setSuccess(false);
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());
        response.setData(null);

        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(MethodArgumentNotValidException e) {
        String errorKey = e.getFieldError().getDefaultMessage();

        ErrorCode errorCode;
        try {
            errorCode = ErrorCode.valueOf(errorKey);
            
        } catch (Exception ex) {
            errorCode = ErrorCode.INTERNAL_VALIDATE_KEY_ERROR;
        }

        return handleAppException(new AppException(errorCode));
    }
}
