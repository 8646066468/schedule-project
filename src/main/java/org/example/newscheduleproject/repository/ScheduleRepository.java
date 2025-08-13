package org.example.newscheduleproject.repository;

import org.example.newscheduleproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    // 로그인 사용자 id로 스케줄 조회
    List<Schedule> findByUserId(Long userId);
}