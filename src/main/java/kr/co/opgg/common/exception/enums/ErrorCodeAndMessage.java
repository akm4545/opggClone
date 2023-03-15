package kr.co.opgg.common.exception.enums;

public interface ErrorCodeAndMessage {
    Integer code = null;
    String message = null;

    Integer getCode();

    String getMessage();
}

