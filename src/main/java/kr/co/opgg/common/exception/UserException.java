package kr.co.opgg.common.exception;

import kr.co.opgg.common.exception.enums.UserErrorCodeAndMessage;
import lombok.Getter;

public class UserException{



    @Getter
    public static class UserExistException extends RuntimeException {
        private final UserErrorCodeAndMessage errorCodeAndMessage = UserErrorCodeAndMessage.Duplicated;
    }

    @Getter
    public static class UserCreateException extends RuntimeException {
        private final UserErrorCodeAndMessage errorCodeAndMessage = UserErrorCodeAndMessage.InsertUserErr;
    }

    @Getter
    public static class UserInfoNotFoundException extends RuntimeException {
        private final UserErrorCodeAndMessage errorCodeAndMessage = UserErrorCodeAndMessage.NotFoundUser;
    }

}
