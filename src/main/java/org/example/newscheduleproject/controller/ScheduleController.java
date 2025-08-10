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
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 생성
    @PostMapping
    public ResponseEntity<ScheduleResponse> saveSchedule(
            @RequestBody ScheduleRequest scheduleRequest
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scheduleService.saveSchedule(scheduleRequest));
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getSchedule());
    }

    // 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequest scheduleRequest
    ) {
        return ResponseEntity
                .ok(scheduleService.Uqdate(scheduleId, scheduleRequest));
    }

    // 삭제
    @DeleteMapping("/{scheduleId}")
    public void  deleteSchedule(
            @PathVariable Long scheduleId
    ) {
        scheduleService.deleteScheduleById(scheduleId);

    }
}

