package kr.co.opgg.common.exception.enums;

import lombok.Getter;

@Getter
public enum JwtTokenErrorCodeAndMessage implements ErrorCodeAndMessage{
    NotCreateToken(1, "토큰 생성에 실패했습니다.");

    private Integer code;

    private String message;

    JwtTokenErrorCodeAndMessage(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
