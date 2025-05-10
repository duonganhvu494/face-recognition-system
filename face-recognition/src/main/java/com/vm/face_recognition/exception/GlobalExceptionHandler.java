package com.vm.face_recognition.exception;

import com.vm.face_recognition.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public <T> ApiResponse<T> handleAppException(AppException exception) {
        return ApiResponse.<T>builder()
                .EC(exception.getErrorCode().getEC())
                .EM(exception.getErrorCode().getEM())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public <T> ApiResponse<T> handleGeneralException(Exception exception) {
        ErrorCode error = ErrorCode.INTERNAL_SERVER_ERROR;
        return ApiResponse.<T>builder()
                .EC(error.getEC())
                .EM(error.getEM())
                .build();
    }

}
