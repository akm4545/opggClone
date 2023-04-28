package kr.co.opgg.common.advice.exception;

import kr.co.opgg.common.exception.JwtTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtTokenExceptionAdvice extends ExceptionAdvice{

    @ExceptionHandler(JwtTokenException.TokenParseException.class)
    public ResponseEntity tokenParseExceptionHandler(JwtTokenException.TokenParseException exception){
        return sendBadRequestWithCodeAndMessage(exception.getErrorCodeAndMessage(), HttpStatus.BAD_REQUEST);
    }
}
