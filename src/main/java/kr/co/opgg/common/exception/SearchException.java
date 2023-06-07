package kr.co.opgg.common.exception;

import kr.co.opgg.common.exception.enums.SearchErrorCodeAndMessage;
import kr.co.opgg.common.exception.enums.UserErrorCodeAndMessage;
import lombok.Getter;

public class SearchException {

    @Getter
    public static class SearchInfoNotFoundException extends RuntimeException {
        private final SearchErrorCodeAndMessage errorCodeAndMessage = SearchErrorCodeAndMessage.NotFoundSearch;
    }

    @Getter
    public static class NotFoundSummonerException extends RuntimeException{
        private final SearchErrorCodeAndMessage errorCodeAndMessage = SearchErrorCodeAndMessage.NotFoundSummoner;
    }

}
