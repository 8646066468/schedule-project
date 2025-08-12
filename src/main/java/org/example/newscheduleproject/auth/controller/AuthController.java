package org.example.newscheduleproject.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> signup(@RequestBody AuthRequest request) {
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료되었습니다");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest, HttpServletRequest request) {
        try {
            AuthResponse result = authService.login(authRequest);
            HttpSession session = request.getSession();
            session.setAttribute("LOGIN_DIRECTOR", result.getId());
            return ResponseEntity.ok("로그인에 성공했습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
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
