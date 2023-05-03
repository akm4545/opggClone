package kr.co.opgg.common.exception.enums;

import lombok.Getter;

@Getter
public enum CommonErrorCodeAndMessage implements ErrorCodeAndMessage {
    EmptyParams(100, "입력 정보가 누락되었습니다."),
    DoesNotExist(101, "존재하지 않는 데이터입니다."),
    InvalidParams(102, "입력 정보 오류입니다."),

    AbnormalAccess(103, "비정상적인 접근입니다.");

    private Integer code;
    private String message;

    CommonErrorCodeAndMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
