package org.example.newscheduleproject.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleRequest {
    @Size(max = 10,message = "제목은 10글자 이내로 입력하셔야합니다.")
    private String title;
    @NotBlank(message = "내용은 필수입니다.")
    private String content;

}
