package org.example.newscheduleproject.service;

import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.ScheduleRequest;
import org.example.newscheduleproject.dto.Response.ScheduleResponse;
import org.example.newscheduleproject.entity.Schedule;
import org.example.newscheduleproject.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    @Transactional
    public ScheduleResponse saveSchedule(ScheduleRequest scheduleRequest) {
        Schedule schedule = new Schedule(scheduleRequest.getName(),scheduleRequest.getTitle(), scheduleRequest.getContent());
        scheduleRepository.save(schedule);

        return ScheduleResponse.from(schedule);

    }
    @Transactional
    public List<ScheduleResponse> getSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponse> scheduleResponseList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleResponseList.add(ScheduleResponse.from(schedule));
        }
        return scheduleResponseList;
    }
    @Transactional
    public ScheduleResponse Uqdate(Long scheduleid, ScheduleRequest scheduleRequest) {
        Schedule schedule = scheduleRepository.findById(scheduleid).orElseThrow(
                () -> new IllegalArgumentException("그런 일정이 없는데?")
        );

        schedule.updatesc(scheduleRequest.getContent(),scheduleRequest.getName(),scheduleRequest.getTitle());
        scheduleRepository.saveAndFlush(schedule);
        return ScheduleResponse.from(schedule);
    }


    public void deleteScheduleById(Long scheduleid) {
        Schedule schedule = scheduleRepository.findById(scheduleid).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디 없는데요?")
        );
        scheduleRepository.delete(schedule);
    }


}
