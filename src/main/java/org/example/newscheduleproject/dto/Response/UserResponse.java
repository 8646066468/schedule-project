package org.example.newscheduleproject.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.newscheduleproject.entity.User;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
    }
}
