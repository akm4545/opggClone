package kr.co.opgg.common.exception;

import kr.co.opgg.common.exception.enums.QNAErrorCodeAndMessage;
import lombok.Getter;

public class QNAException {
    public static final QNAException.NotUpdateException NOT_UPDATE_EXCEPTION = new QNAException.NotUpdateException();

    @Getter
    public static class NotUpdateException extends RuntimeException {
        private final QNAErrorCodeAndMessage errorCodeAndMessage = QNAErrorCodeAndMessage.NotUpdate;
    }
}
