package org.example.newscheduleproject.controller;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.UserRequest;
import org.example.newscheduleproject.dto.Response.UserResponse;
import org.example.newscheduleproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    // 전체 조회
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllSchedules() {
        return ResponseEntity.ok(userService.getUser());
    }

    // 수정
    @PutMapping
    public ResponseEntity<UserResponse> updateSchedule(
            HttpSession session,
            @RequestBody @Valid UserRequest userRequest
    ) {
        return ResponseEntity
                .ok(userService.Update(session, userRequest));
    }

    // 삭제
    @DeleteMapping
    public void  deleteSchedule(
            HttpSession session

    ) {
        userService.deleteUserById(session);

    }

}
