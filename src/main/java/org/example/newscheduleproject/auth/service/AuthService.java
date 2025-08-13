package org.example.newscheduleproject.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.auth.dto.AuthLoginRequest;
import org.example.newscheduleproject.auth.dto.AuthRequest;
import org.example.newscheduleproject.auth.dto.AuthResponse;
import org.example.newscheduleproject.common.filter.PasswordEncoder;
import org.example.newscheduleproject.entity.User;
import org.example.newscheduleproject.error.CustomException;
import org.example.newscheduleproject.error.ErrorCode;
import org.example.newscheduleproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResponse signup(final AuthRequest request) {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_USED);
        }

        String passwordEncoded = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getName(), request.getEmail(), passwordEncoded);
        userRepository.save(user);
        return new AuthResponse(user.getId());
    }

    @Transactional
    public AuthResponse login(final AuthLoginRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.EMAIL_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        return new AuthResponse(user.getId());
    }
}
