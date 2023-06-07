package kr.co.opgg.common.exception.enums;

import lombok.Getter;

@Getter
public enum SearchErrorCodeAndMessage implements ErrorCodeAndMessage{

    NotFoundSearch(1, "검색한 데이터를 찾을 수 없습니다."),
    NotFoundSummoner(2 , "소환사 정보를 찾을 수 없습니다.");

    private Integer code;
    private String message;
    SearchErrorCodeAndMessage(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
