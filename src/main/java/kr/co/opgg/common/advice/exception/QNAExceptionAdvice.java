package kr.co.opgg.common.advice.exception;

import kr.co.opgg.common.exception.QNAException;
import kr.co.opgg.common.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QNAExceptionAdvice extends ExceptionAdvice{

    @ExceptionHandler(QNAException.NotUpdateException.class)
    public ResponseEntity notUpdateExceptionHandler(QNAException.NotUpdateException exception) {
        return sendBadRequestWithCodeAndMessage(exception.getErrorCodeAndMessage(), HttpStatus.BAD_REQUEST);
    }
}
