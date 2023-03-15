package kr.co.opgg.common.exception.dto;

import kr.co.opgg.common.exception.enums.ErrorCodeAndMessage;

public class ExceptionWithDataResponse extends ExceptionResponse{
    Object data = null;

    public ExceptionWithDataResponse(ErrorCodeAndMessage errorCodeAndMessage, Object object){
        super(errorCodeAndMessage);

        data = object;
    }
}
