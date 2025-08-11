package org.example.newscheduleproject.auth.service;

import lombok.RequiredArgsConstructor;
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
    public void signup(AuthRequest request) {
        User user = new User(request.getName(), request.getEmail(), request.getPassword());
        userRepository.save(user);
    }
    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepository.findByName(authRequest.getName())
                .orElseThrow(() -> new IllegalArgumentException("아이디가 일치하지 않습니다."));

        // 비밀번호 검증
        if (!user.getPassword().equals(authRequest.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new AuthResponse(user.getId());

    }
}
