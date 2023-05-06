package kr.co.opgg.apis.qna.dto;

import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

public class QNARequest {

    @Data
    public static class InsertQAN{
        @NotNull
        String qnaTitle;

        @NotNull
        String qnaContent;
    }

    @Data
    public static class UpdateQAN{
        @NotNull
        Integer qnaIdx;

        @NotNull
        String qnaTitle;

        @NotNull
        String qnaContent;
    }

    @Data
    public static class SelectQAN{
        @NotNull
        Integer qnaIdx;
    }
}
