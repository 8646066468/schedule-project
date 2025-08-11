package org.example.newscheduleproject.controller;


import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.service.ScheduleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users/userId/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;


}

