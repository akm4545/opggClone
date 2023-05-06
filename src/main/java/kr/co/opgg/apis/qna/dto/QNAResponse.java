package kr.co.opgg.apis.qna.dto;

import kr.co.opgg.datasource.qna.QNA;
import lombok.Builder;
import lombok.Data;

public class QNAResponse {

    @Data
    @Builder
    public static class SelectQna{
        Integer qnaIdx;

        String qnaTitle;

        String qnaContent;

        String qnaAnswer;

        public static SelectQna domainToDto(QNA qna){
            return SelectQna.builder()
                    .qnaIdx(qna.getQnaIdx())
                    .qnaTitle(qna.getQnaTitle())
                    .qnaContent(qna.getQnaContent())
                    .build();
        }
    }
}
