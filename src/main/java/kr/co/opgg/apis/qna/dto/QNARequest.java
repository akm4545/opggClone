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
    public static class insertQAN{
        @NotNull
        String qnaTitle;

        @NotNull
        String qnaContent;
    }
}
