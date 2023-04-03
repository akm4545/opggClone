package kr.co.opgg.common.exception.enums;

import lombok.Getter;

@Getter
public class UserErrorCodeAndMessage implements ErrorCodeAndMessage{
    private Integer code;
    private String message;
    public UserErrorCodeAndMessage(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
