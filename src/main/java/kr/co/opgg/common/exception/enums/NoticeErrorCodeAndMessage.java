package kr.co.opgg.common.exception.enums;

import lombok.Getter;

@Getter
public enum NoticeErrorCodeAndMessage implements ErrorCodeAndMessage{
    test(1, "테스트예외입니다.");

    private Integer code;
    private String message;

    NoticeErrorCodeAndMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
