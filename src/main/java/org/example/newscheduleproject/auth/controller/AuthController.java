package org.example.newscheduleproject.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.auth.dto.AuthRequest;
import org.example.newscheduleproject.auth.dto.AuthResponse;
import org.example.newscheduleproject.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody AuthRequest request) {
        authService.signup(request);
        return "회원가입에 성공했습니다.";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest, HttpServletRequest request) {
        AuthResponse result = authService.login(authRequest);

        HttpSession session = request.getSession();
        session.setAttribute("LOGIN_DIRECTOR", result.getId());
        return "로그인에 성공했습니다.";
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }
    }
}
