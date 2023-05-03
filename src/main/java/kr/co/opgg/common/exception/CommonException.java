package kr.co.opgg.common.exception;

import kr.co.opgg.common.exception.enums.CommonErrorCodeAndMessage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindingResult;

public class CommonException {

    public static final EmptyParamsException EMPTY_PARAMS_EXCEPTION =
            new EmptyParamsException();
    public static final InvalidParamsException INVALID_PARAMS_EXCEPTION =
            new InvalidParamsException();
    public static final DoesNotExistException DOES_NOT_EXIST_EXCEPTION =
            new DoesNotExistException();

    public static final AbnormalAccessException ABNORMAL_ACCESS_EXCEPTION =
            new AbnormalAccessException();

    @Getter
    public static class EmptyParamsException extends RuntimeException {
        private final CommonErrorCodeAndMessage errorCodeAndMessage =
                CommonErrorCodeAndMessage.EmptyParams;
    }

    @Getter
    @Setter
    public static class InvalidParamsException extends RuntimeException {
        private BindingResult bindingResult;
        private final CommonErrorCodeAndMessage errorCodeAndMessage =
                CommonErrorCodeAndMessage.InvalidParams;
    }

    @Getter
    public static class DoesNotExistException extends RuntimeException {
        private final CommonErrorCodeAndMessage errorCodeAndMessage =
                CommonErrorCodeAndMessage.DoesNotExist;
    }

    @Getter
    public static class AbnormalAccessException extends RuntimeException {
        private final CommonErrorCodeAndMessage errorCodeAndMessage =
                CommonErrorCodeAndMessage.AbnormalAccess;
    }
}
