package kr.co.opgg.common.exception.enums;

import lombok.Getter;

@Getter
public enum UserErrorCodeAndMessage implements ErrorCodeAndMessage{
    Duplicated(1, "중복된 계정입니다."),

    InsertUserErr(2, "유저를 등록중 에러가 발생했습니다."),

    NotFoundUser(3, "유저 정보를 찾을 수 없습니다.");
    private Integer code;
    private String message;
    UserErrorCodeAndMessage(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
