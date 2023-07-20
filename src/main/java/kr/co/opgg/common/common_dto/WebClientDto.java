package kr.co.opgg.common.common_dto;

import lombok.*;


public class WebClientDto {


    @Getter
    @ToString
    public static class BasicDto{
        private String contentType;

        @Builder
        public BasicDto(String baseURL, String reqType, String contentType){
            this.contentType = contentType;
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    public static class ReqWebClientDto{
        private String requestURL;

        private String baseURL;

        private BasicDto basicDto;

        private String reqType;

        @Builder
        public ReqWebClientDto(String requestURL, String baseURL, String reqType, BasicDto basicDto){
            this.requestURL = requestURL;
            this.baseURL = baseURL;
            this.basicDto = basicDto;
            this.reqType = reqType;
        }
    }

}
