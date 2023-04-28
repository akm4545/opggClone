package kr.co.opgg.common.exception;

import kr.co.opgg.common.exception.enums.JwtTokenErrorCodeAndMessage;
import lombok.Getter;

public class JwtTokenException {



    @Getter
    public static class NotCreateToken extends RuntimeException{
        private final JwtTokenErrorCodeAndMessage errorCodeAndMessage = JwtTokenErrorCodeAndMessage.NotCreateToken;
    }

    @Getter
    public static class TokenParseException extends RuntimeException{
        private final JwtTokenErrorCodeAndMessage errorCodeAndMessage = JwtTokenErrorCodeAndMessage.TokenParseException;
    }

    @Getter
    public static class ExpiredToken extends RuntimeException{
        private final JwtTokenErrorCodeAndMessage errorCodeAndMessage = JwtTokenErrorCodeAndMessage.ExpiredToken;
    }
}
