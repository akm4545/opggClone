package kr.co.opgg.apis.faq.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

public class FAQRequest {
    @Data
    public static class FAQ{
        @NotNull
        private Integer faqIdx;

        private String faqTitle;

        private String faqContent;

        private String createDate;

        private String updateDate;

        private String deleteDate;

        private Integer userIdx;
    }

    @Data
    public static class FAQCategory{
        @NotNull
        private String category;
    }
}
