package org.example.newscheduleproject.auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginRequest {
    private String email;
    private String password;
}
