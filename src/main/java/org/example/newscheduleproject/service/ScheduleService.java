package org.example.newscheduleproject.service;


import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.repository.ScheduleRepository;
import org.example.newscheduleproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

}
