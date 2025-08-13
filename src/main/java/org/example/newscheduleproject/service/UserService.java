package org.example.newscheduleproject.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.UserDeleteSchedule;
import org.example.newscheduleproject.dto.Request.UserRequest;
import org.example.newscheduleproject.dto.Response.UserResponse;
import org.example.newscheduleproject.entity.User;
import org.example.newscheduleproject.error.CustomException;
import org.example.newscheduleproject.error.ErrorCode;
import org.example.newscheduleproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public List<UserResponse> getUser() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users) {
            userResponseList.add(UserResponse.from(user));
        }
        return userResponseList;
    }
    @Transactional
    public UserResponse Update(HttpSession session, final UserRequest userRequest) {
        Long loginUserId  = (Long) session.getAttribute("LOGIN_DIRECTOR");

        User user = userRepository.findById(loginUserId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );

        user.update(userRequest.getName(),userRequest.getEmail());
        userRepository.save(user);
        return UserResponse.from(user);

    }
    //삭제
    @Transactional
    public void deleteUserById(HttpSession session,final UserDeleteSchedule userDeleteRequest) {
        Long loginUserId  = (Long) session.getAttribute("LOGIN_DIRECTOR");
        User user = userRepository.findById(loginUserId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );

        userRepository.delete(user);
    }
}
