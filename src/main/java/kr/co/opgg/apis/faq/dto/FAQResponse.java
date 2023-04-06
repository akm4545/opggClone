package kr.co.opgg.apis.faq.dto;

import lombok.Builder;
import lombok.Data;

public class FAQResponse {
    @Data
    @Builder
    public static class FAQ{
        private Integer faqIdx;

        private String faqTitle;

        private String faqContent;
    }
}
