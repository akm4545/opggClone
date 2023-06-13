package kr.co.opgg.common.common_dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


public class WebClientDto {


    @Getter
    @ToString
    public static class BasicDto{
        private String baseURL;

        private String reqType;

        private String contentType;

        @Builder
        public BasicDto(String baseURL, String reqType, String contentType){
            this.baseURL = baseURL;
            this.reqType = reqType;
            this.contentType = contentType;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class ReqWebClientDto{
        private String requestURL;

        private BasicDto basicDto;
    }

}
