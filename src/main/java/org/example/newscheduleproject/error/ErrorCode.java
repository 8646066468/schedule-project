package org.example.newscheduleproject.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT("VAL-001", "입력값이 유효하지 않습니다"),
    DUPLICATE_USER("USR-001", "이미 가입된 사용자입니다"),
    UNDERAGE_USER("USR-002", "18세 미만은 가입할 수 없습니다"),
    USER_LIMIT_EXCEEDED("USR-003", "가입 가능한 사용자 수를 초과했습니다");

    private final String code;
    private final String message;

}

