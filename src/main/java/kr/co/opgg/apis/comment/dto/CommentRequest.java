package kr.co.opgg.apis.comment.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

public class CommentRequest {

    @Data
    public static class InsertComment{
        @NotNull
        Integer boardIdx;

        Integer parentCommentIdx;

        @NotNull
        String commentContent;
    }

    @Data
    public static class UpdateComment{
        @NotNull
        Integer boardIdx;

        @NotNull
        Integer commentIdx;

        @NotNull
        String commentContent;
    }

    @Data
    public static class DeleteComment{
        @NotNull
        Integer boardIdx;

        @NotNull
        Integer commentIdx;
    }

    @Data
    public static class RecommendComment{
        @NotNull
        Integer commentIdx;
    }
}
