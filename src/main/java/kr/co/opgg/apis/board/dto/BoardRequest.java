package kr.co.opgg.apis.board.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

public class BoardRequest {

    @Data
    public static class BoardListSearchCondition {
        private String sort;

        private String category;
    }

    @Data
    public static class Board{
        @NotNull
        private Integer boardIdx;
    }

    @Data
    public static class BoardDetail{
        @NotNull
        private String title;

        @NotNull
        private String boardType;

        @NotNull
        private String content;
    }

    @Data
    public static class BoardDetailUpdate{
        @NotNull
        private Integer boardIdx;

        @NotNull
        private String title;

        @NotNull
        private String boardType;

        @NotNull
        private String content;

        List<Integer> removeFileList;
    }
}
