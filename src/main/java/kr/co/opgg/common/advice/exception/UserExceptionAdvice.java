package kr.co.opgg.common.advice.exception;

import kr.co.opgg.common.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionAdvice extends ExceptionAdvice{

    @ExceptionHandler(UserException.UserExistException.class)
    public ResponseEntity userExceptionHandler(UserException.UserExistException exception) {
        return sendBadRequestWithCodeAndMessage(exception.getErrorCodeAndMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.UserCreateException.class)
    public ResponseEntity userCreateExceptionHandler(UserException.UserCreateException exception) {
        return sendBadRequestWithCodeAndMessage(exception.getErrorCodeAndMessage(), HttpStatus.BAD_REQUEST);
    }
}
