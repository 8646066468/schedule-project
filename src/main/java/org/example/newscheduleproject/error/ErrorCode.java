package org.example.newscheduleproject.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 사용자 관련
    USER_NOT_FOUND("USR-001", "사용자를 찾을 수 없습니다."),
    USER_PASSWORD_NOT_EQUALS("USR-002", "비밀번호가 일치하지 않습니다."),
    USER_SCHEDULE_NOT_MATCH("USR-003", "사용자와 스케줄이 일치하지 않습니다."),
    USER_LIMIT_EXCEEDED("USR-004", "가입 가능한 사용자 수를 초과했습니다."),

    // 스케줄 관련
    SCHEDULE_NOT_FOUND("SCH-001", "스케줄을 찾을 수 없습니다."),

    // 인증 / 권한 관련
    UNAUTHORIZED("AUTH-001", "권한이 없습니다. 로그인 해주세요."),

    // 입력값 관련
    INVALID_INPUT("VAL-001", "입력값이 유효하지 않습니다."),

    // 이메일 / 로그인 관련
    EMAIL_ALREADY_USED("AUTH-002", "이미 사용 중인 이메일입니다."),
    EMAIL_NOT_FOUND("AUTH-003", "존재하지 않는 이메일입니다."),
    PASSWORD_NOT_MATCH("AUTH-004", "비밀번호가 일치하지 않습니다."),

    // 기타
    DUPLICATE_USER("USR-005", "이미 존재하는 사용자입니다.");

    private final String code;
    private final String message;

}

