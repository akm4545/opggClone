package kr.co.opgg.common.exception.enums;

import kr.co.opgg.common.exception.JwtTokenException;
import lombok.Getter;

@Getter
public enum JwtTokenErrorCodeAndMessage implements ErrorCodeAndMessage{
    NotCreateToken(1, "토큰 생성에 실패했습니다."),
    TokenParseException(2, "토큰 파싱에 실패했습니다."),
    ExpiredToken(3, "이미 만료된 토큰입니다.");

    private Integer code;

    private String message;

    JwtTokenErrorCodeAndMessage(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
