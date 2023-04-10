package kr.co.opgg.apis.faq.dto;

import kr.co.opgg.datasource.faq.FAQ;
import lombok.Builder;
import lombok.Data;

public class FAQResponse {
    @Data
    @Builder
    public static class FAQ{
        private Integer faqIdx;

        private String faqTitle;

        private String faqContent;

        public static FAQ domainToDto(kr.co.opgg.datasource.faq.FAQ faq){
            return FAQ.builder()
                    .faqContent(faq.getFaqContent())
                    .faqTitle(faq.getTitle())
                    .faqIdx(faq.getFaqIdx())
                    .build();
        }
    }
}
