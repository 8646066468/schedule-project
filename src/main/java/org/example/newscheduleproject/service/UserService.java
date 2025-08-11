package org.example.newscheduleproject.service;




import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.UserRequest;
import org.example.newscheduleproject.dto.Response.UserResponse;
import org.example.newscheduleproject.entity.User;
import org.example.newscheduleproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    
    public UserResponse saveUser(UserRequest userRequest) {
        User user = new User(userRequest.getName(), userRequest.getEmail());
        userRepository.save(user);

        return UserResponse.from(user);
    }

    public List<UserResponse> getUser() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : users) {
            userResponseList.add(UserResponse.from(user));
        }
        return userResponseList;
    }

    public UserResponse Uqdate(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 일정이 없는데?")
        );

        user.update(userRequest.getName(),userRequest.getEmail());
        userRepository.save(user);
        return UserResponse.from(user);
    
    }

    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디 없는데요?")
        );
        userRepository.delete(user);
    }
}
