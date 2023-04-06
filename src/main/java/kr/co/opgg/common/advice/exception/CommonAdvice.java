package kr.co.opgg.common.advice.exception;

import kr.co.opgg.common.exception.CommonException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonAdvice extends ExceptionAdvice {
    @ExceptionHandler(CommonException.EmptyParamsException.class)
    public ResponseEntity emptyParamsExceptionHandler(CommonException.EmptyParamsException exception) {
        return sendBadRequestWithCodeAndMessage(exception.getErrorCodeAndMessage());
    }

    @ExceptionHandler(CommonException.InvalidParamsException.class)
    public ResponseEntity invalidParamsHandler(CommonException.InvalidParamsException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        return sendBadRequestWithCodeAndMessageAndErrors(exception.getErrorCodeAndMessage(), bindingResult);
    }

    @ExceptionHandler(CommonException.DoesNotExistException.class)
    public ResponseEntity doesNotExistExceptionHandler(CommonException.DoesNotExistException exception) {
        return sendBadRequestWithCodeAndMessage(exception.getErrorCodeAndMessage());
    }
}
