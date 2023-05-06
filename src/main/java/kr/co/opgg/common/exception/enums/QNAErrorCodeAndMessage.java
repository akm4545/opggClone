package kr.co.opgg.common.exception.enums;

import lombok.Getter;

@Getter
public enum QNAErrorCodeAndMessage implements ErrorCodeAndMessage{

    NotUpdate(201, "수정 불가능한 질문입니다.");

    private Integer code;

    private String message;

    QNAErrorCodeAndMessage(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
