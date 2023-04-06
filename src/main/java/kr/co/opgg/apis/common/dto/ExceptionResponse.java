package kr.co.opgg.apis.common.dto;

import kr.co.opgg.common.exception.enums.ErrorCodeAndMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private Integer code;
    private String message;

    public ExceptionResponse(ErrorCodeAndMessage errorCodeAndMessage){
        code = errorCodeAndMessage.getCode();
        message = errorCodeAndMessage.getMessage();
    }

}
