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

    @Data
    public static class updateComment{
        @NotNull
        Integer boardIdx;

        @NotNull
        Integer commentIdx;

        @NotNull
        String commentContent;
    }

    @Data
    public static class deleteComment{
        @NotNull
        Integer boardIdx;

        @NotNull
        Integer commentIdx;
    }
}
