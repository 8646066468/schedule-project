package org.example.newscheduleproject.controller;



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
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateSchedule(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest
    ) {
        return ResponseEntity
                .ok(userService.Uqdate(userId, userRequest));
    }

    // 삭제
    @DeleteMapping("/{userId}")
    public void  deleteSchedule(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest
    ) {
        userService.deleteUserById(userId, userRequest);

    }

}
