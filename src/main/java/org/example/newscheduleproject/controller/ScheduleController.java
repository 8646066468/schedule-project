package org.example.newscheduleproject.controller;


import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.ScheduleRequest;
import org.example.newscheduleproject.dto.Response.ScheduleResponse;
import org.example.newscheduleproject.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    //전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAll(
            @PathVariable Long userId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getFindAll(userId));
    }
    //단건 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> update(
            @PathVariable Long userId,
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequest scheduleRequest
    ){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(userId,scheduleId,scheduleRequest));
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long userId,
            @PathVariable Long scheduleId
    ) {
        scheduleService.deleteSchedule(userId,scheduleId);
        return ResponseEntity.ok().build();  // 200 OK, 바디 없음
    }



}




