package org.example.newscheduleproject.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.auth.dto.AuthLoginRequest;
import org.example.newscheduleproject.auth.dto.AuthRequest;
import org.example.newscheduleproject.auth.dto.AuthResponse;
import org.example.newscheduleproject.entity.User;
import org.example.newscheduleproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    @Transactional
    public AuthResponse signup(final AuthRequest request) {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }


        User user = new User(request.getName(),request.getEmail(), request.getPassword());
        userRepository.save(user);
        return new AuthResponse(user.getId());
    }

    @Transactional
    public AuthResponse login(final AuthLoginRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 일치하지 않습니다."));

        // 비밀번호 검증
        if (!user.getPassword().equals(authRequest.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        //글로벌 입셉션 GlobalExceptionHandler
        //커스텀 예외처리

        return new AuthResponse(user.getId());

    }
}
