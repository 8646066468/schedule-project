
package org.example.newscheduleproject.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.ScheduleRequest;
import org.example.newscheduleproject.dto.Response.ScheduleResponse;
import org.example.newscheduleproject.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/{userId}/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleResponse createSchedule(
            @PathVariable Long userId,
            @RequestBody ScheduleRequest request,
            HttpSession session
    ) {

        return scheduleService.saveSchedule(session, userId, request);
    }

    @GetMapping
    public List<ScheduleResponse> getSchedules(
            @PathVariable Long userId,
            HttpSession session
    ) {
        return scheduleService.getSchedules(session, userId);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponse updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequest request,
            HttpSession session
    ) {
        return scheduleService.updateSchedule(session, scheduleId, request);
    }

    @DeleteMapping("/{scheduleId}")
    public void deleteSchedule(
            @PathVariable Long scheduleId,
            HttpSession session
    ) {
        scheduleService.deleteSchedule(session, scheduleId);
    }
}
