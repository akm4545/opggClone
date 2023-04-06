package kr.co.opgg.apis.common.dto;

import kr.co.opgg.common.exception.enums.ErrorCodeAndMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionWithDataResponse extends ExceptionResponse{

    Object data = null;

    public ExceptionWithDataResponse(ErrorCodeAndMessage errorCodeAndMessage, Object object){
        super(errorCodeAndMessage);

        data = object;
    }

}
