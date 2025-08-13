package org.example.newscheduleproject.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getCode(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse error = ErrorResponse.of(HttpStatus.BAD_REQUEST, "VAL-000", message, request.getRequestURI());
        return ResponseEntity.badRequest().body(error);
    }
}
