package kr.co.opgg.common.advice.exception;

import kr.co.opgg.common.exception.NoticeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NoticeExceptionAdvice extends ExceptionAdvice {

    @ExceptionHandler(NoticeException.TestException.class)
    public ResponseEntity noticeExceptionHandler(NoticeException.TestException exception) {
        return sendBadRequestWithCodeAndMessage(exception.getErrorCodeAndMessage(), HttpStatus.BAD_REQUEST);
    }
}
