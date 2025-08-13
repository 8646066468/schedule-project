package org.example.newscheduleproject.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.auth.dto.AuthLoginRequest;
import org.example.newscheduleproject.auth.dto.AuthRequest;
import org.example.newscheduleproject.auth.dto.AuthResponse;
import org.example.newscheduleproject.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody AuthRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(request));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthLoginRequest authloginRequest, HttpServletRequest request) {
        try {
            AuthResponse result = authService.login(authloginRequest);
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_DIRECTOR", result.getId());
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 삭제
        }
        return ResponseEntity.noContent().build();
    }
}
