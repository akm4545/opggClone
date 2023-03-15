package kr.co.opgg.common.advice.exception;

import kr.co.opgg.common.exception.dto.ExceptionResponse;
import kr.co.opgg.common.exception.dto.ExceptionWithDataResponse;
import kr.co.opgg.common.exception.dto.ExceptionWithErrorResponse;
import kr.co.opgg.common.exception.enums.ErrorCodeAndMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ExceptionAdvice {
    public ResponseEntity sendBadRequestWithCodeAndMessage(ErrorCodeAndMessage errorCodeAndMessage) {
        ExceptionResponse failResponseDto = new ExceptionResponse(errorCodeAndMessage);
        return new ResponseEntity<>(failResponseDto, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity sendBadRequestWithCodeAndMessage(ErrorCodeAndMessage errorCodeAndMessage, HttpStatus httpStatus) {
        ExceptionResponse failResponseDto = new ExceptionResponse(errorCodeAndMessage);
        return new ResponseEntity<>(failResponseDto, httpStatus);
    }

    public ResponseEntity sendBadRequestWithCodeAndMessageAndErrors(ErrorCodeAndMessage errorCodeAndMessage, BindingResult bindingResult) {
        ExceptionResponse failResponseDto = new ExceptionWithErrorResponse(errorCodeAndMessage, bindingResult);
        return new ResponseEntity<>(failResponseDto, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity sendBadRequestWithCodeAndMessageAndData(ErrorCodeAndMessage errorCodeAndMessage, Object data) {
        ExceptionResponse failResponseDto = new ExceptionWithDataResponse(errorCodeAndMessage, data);
        return new ResponseEntity<>(failResponseDto, HttpStatus.BAD_REQUEST);
    }
}
