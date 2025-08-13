package org.example.newscheduleproject.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String errorCode;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public static ErrorResponse of(HttpStatus status, String errorCode, String message, String path) {
        return new ErrorResponse(status.value(), errorCode, message, path, LocalDateTime.now());
    }
}

