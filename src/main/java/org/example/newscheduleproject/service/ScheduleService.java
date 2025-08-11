package org.example.newscheduleproject.service;


import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.ScheduleRequest;
import org.example.newscheduleproject.dto.Response.ScheduleResponse;
import org.example.newscheduleproject.dto.Response.UserResponse;
import org.example.newscheduleproject.entity.Schedule;
import org.example.newscheduleproject.entity.User;
import org.example.newscheduleproject.repository.ScheduleRepository;
import org.example.newscheduleproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    //전체조회
    @Transactional
    public List<ScheduleResponse> getFindAll(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 id 없어요")
        );
        //특정 사용자 스케줄 조회
        List<Schedule> schedules = scheduleRepository.findByUser(user);;

        return schedules.stream().map(ScheduleResponse::from).collect(Collectors.toList());

    }

    //수정
    @Transactional
    public ScheduleResponse updateSchedule(Long userId, Long scheduleId, ScheduleRequest scheduleRequest) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 id 없어요")
        );
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("그런 id 없어요")
        );
        if (!schedule.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("사용자와 스케줄이 일치하지 않습니다.");
        }

        schedule.update(scheduleRequest.getTitle(),scheduleRequest.getContent());
        scheduleRepository.save(schedule);
        return ScheduleResponse.from(schedule);
    }

    public void deleteSchedule(Long userId, Long scheduleId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 id 없어요")
        );
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("그런 id 없어요")
        );
        if (!schedule.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("사용자와 스케줄이 일치하지 않습니다.");
        }
        scheduleRepository.delete(schedule);
    }
}
