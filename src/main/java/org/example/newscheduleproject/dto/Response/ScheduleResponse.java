package org.example.newscheduleproject.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.newscheduleproject.entity.Schedule;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponse {
    private final Long id;
    private final String name;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(schedule.getId(), schedule.getName(), schedule.getTitle(), schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }
}
