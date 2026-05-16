package com.medical.appointmentservice.exception;

import com.medical.appointmentservice.dto.response.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ApiResponseError> handleResourceNotFound(ResourceNotFoundException ex)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(
                        ApiResponseError
                                .builder()
                                .status(HttpStatus.NOT_FOUND)
                                .error("Resource not found.")
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(TooManyRequestException.class)
    private ResponseEntity<ApiResponseError> handleTooManyRequest(TooManyRequestException ex)
    {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS.value())
                .body(
                        ApiResponseError
                                .builder()
                                .status(HttpStatus.TOO_MANY_REQUESTS)
                                .error("Too many request.")
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    private ResponseEntity<ApiResponseError> handleServiceUnavailable(ServiceUnavailableException ex)
    {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .body(
                        ApiResponseError
                                .builder()
                                .status(HttpStatus.SERVICE_UNAVAILABLE)
                                .error("Service is unavailable.")
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiResponseError> handleException(Exception ex)
    {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(
                        ApiResponseError
                                .builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .error("Internal server error.")
                                .timestamp(LocalDateTime.now())
                                .message(ex.getMessage())
                                .build()
                );
    }
}
