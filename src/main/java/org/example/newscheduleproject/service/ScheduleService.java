package org.example.newscheduleproject.service;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newscheduleproject.dto.Request.ScheduleRequest;
import org.example.newscheduleproject.dto.Response.ScheduleResponse;
import org.example.newscheduleproject.entity.Schedule;
import org.example.newscheduleproject.entity.User;
import org.example.newscheduleproject.error.CustomException;
import org.example.newscheduleproject.error.ErrorCode;
import org.example.newscheduleproject.repository.ScheduleRepository;
import org.example.newscheduleproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 로그인 사용자 조회 공통 메서드
    private User getLoginUser(HttpSession session) {
        Long loginUserId = (Long) session.getAttribute("LOGIN_DIRECTOR");
        if (loginUserId == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        return userRepository.findById(loginUserId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional
    public ScheduleResponse saveSchedule(HttpSession session,Long userId, ScheduleRequest request) {
        User loginUser = getLoginUser(session);
        if (!loginUser.getId().equals(userId)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        Schedule schedule = new Schedule(request.getTitle(), request.getContent(), loginUser);
        scheduleRepository.save(schedule);
        return ScheduleResponse.from(schedule);
    }

    @Transactional
    public List<ScheduleResponse> getSchedules(HttpSession session, Long userId) {
        User loginUser = getLoginUser(session);

        if (!loginUser.getId().equals(userId)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        List<Schedule> schedules = scheduleRepository.findByUserId(userId);
        return schedules.stream().map(ScheduleResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public ScheduleResponse updateSchedule(HttpSession session, Long scheduleId, ScheduleRequest request) {
        User loginUser = getLoginUser(session);

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        if (!schedule.getUser().getId().equals(loginUser.getId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        schedule.update(request.getTitle(), request.getContent());
        return ScheduleResponse.from(schedule);
    }

    @Transactional
    public void deleteSchedule(HttpSession session, Long scheduleId) {
        User loginUser = getLoginUser(session);

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        if (!schedule.getUser().getId().equals(loginUser.getId())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        scheduleRepository.delete(schedule);
    }
}
