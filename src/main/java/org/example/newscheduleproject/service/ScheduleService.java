package org.example.newscheduleproject.service;


import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.ScheduleRequest;
import org.example.newscheduleproject.dto.Response.ScheduleResponse;
import org.example.newscheduleproject.entity.Schedule;
import org.example.newscheduleproject.entity.User;
import org.example.newscheduleproject.repository.ScheduleRepository;
import org.example.newscheduleproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    //생성
    @Transactional
    public ScheduleResponse saveSchedule(ScheduleRequest scheduleRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 id 없어요")
        );
        Schedule schedule = new Schedule(
                scheduleRequest.getTitle(),
                scheduleRequest.getContent(),
                user
        );
        scheduleRepository.save(schedule);
        return ScheduleResponse.from(schedule);

    }
}
