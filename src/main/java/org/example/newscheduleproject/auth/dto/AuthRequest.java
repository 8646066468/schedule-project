package org.example.newscheduleproject.auth.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthRequest {
    @NotBlank(message = "이름은 필수입니다.")
    @Size(max=4,message = "유저명은 4글자까지 입력 할 수 있습니다.")
    private String name;
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이여야 합니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자리여야합니다.")
    private String password;
}
