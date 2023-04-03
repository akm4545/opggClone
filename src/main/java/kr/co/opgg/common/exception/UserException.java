package kr.co.opgg.common.exception;

import kr.co.opgg.common.exception.enums.UserErrorCodeAndMessage;
import lombok.Getter;

public class UserException{

    @Getter
    public static class UserExistException extends RuntimeException {
        private final UserErrorCodeAndMessage errorCodeAndMessage = new UserErrorCodeAndMessage(1, "중복된 계정입니다.");
    }

    @Getter
    public static class UserCreateException extends RuntimeException {
        private final UserErrorCodeAndMessage errorCodeAndMessage = new UserErrorCodeAndMessage(2, "유저를 등록중 에러가 발생했습니다.");
    }

}
