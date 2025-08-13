package org.example.newscheduleproject.service;

import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.UserDeleteSchedule;
import org.example.newscheduleproject.dto.Request.UserRequest;
import org.example.newscheduleproject.dto.Response.UserResponse;
import org.example.newscheduleproject.entity.User;
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
    public UserResponse Update(final Long userId,final UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 일정이 없는데?")
        );
        if (!userRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않다");
        }// t  userentity에 비밀번호 알고리즘을 넣어보자

        user.update(userRequest.getName(),userRequest.getEmail());
        userRepository.save(user);
        return UserResponse.from(user);

    }
    //삭제
    @Transactional
    public void deleteUserById(final Long userId,final UserDeleteSchedule userDeleteScheduleRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디 없는데요?")
        );
        if (!userDeleteScheduleRequest.getPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않다");
        }
        userRepository.delete(user);
    }
}
