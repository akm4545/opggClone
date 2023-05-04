package kr.co.opgg.apis.comment.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

public class CommentRequest {

    @Data
    public static class insertComment{
        @NotNull
        Integer boardIdx;

        Integer parentCommentIdx;

        @NotNull
        String commentContent;
    }
}
