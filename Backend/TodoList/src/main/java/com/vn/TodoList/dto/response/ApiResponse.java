package com.vn.TodoList.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String message;
    private T data;
    private boolean success = true; // Default success is true, can be overridden
    private int code = 0; // Default code is 0, can be overridden

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
