package kr.co.opgg.common.advice.exception;

import kr.co.opgg.common.exception.SearchException;
import kr.co.opgg.common.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SearchExceptionAdvice extends ExceptionAdvice{

    @ExceptionHandler(SearchException.SearchInfoNotFoundException.class)
    public ResponseEntity userExceptionHandler(SearchException.SearchInfoNotFoundException exception) {
        return sendBadRequestWithCodeAndMessage(exception.getErrorCodeAndMessage(), HttpStatus.BAD_REQUEST);
    }
}
