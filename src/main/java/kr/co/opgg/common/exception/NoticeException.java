package kr.co.opgg.common.exception;

import kr.co.opgg.common.exception.enums.NoticeErrorCodeAndMessage;
import lombok.Getter;

public class NoticeException {
    public static final TestException TEST_EXCEPTION = new TestException();

    @Getter
    public static class TestException extends RuntimeException {
        private final NoticeErrorCodeAndMessage errorCodeAndMessage = NoticeErrorCodeAndMessage.test;
    }
}
