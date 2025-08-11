package org.example.newscheduleproject.repository;

import org.example.newscheduleproject.entity.Schedule;
import org.example.newscheduleproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    List<Schedule> findByUser(User user);
}