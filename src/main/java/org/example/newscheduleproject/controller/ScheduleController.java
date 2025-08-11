package org.example.newscheduleproject.controller;


import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.ScheduleRequest;
import org.example.newscheduleproject.dto.Request.UserRequest;
import org.example.newscheduleproject.dto.Response.ScheduleResponse;
import org.example.newscheduleproject.entity.Schedule;
import org.example.newscheduleproject.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    //생성
    @PostMapping
    public ResponseEntity<ScheduleResponse> save(
            @PathVariable Long userId,
            @RequestBody ScheduleRequest scheduleRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.saveSchedule(scheduleRequest,userId));
    }
}




