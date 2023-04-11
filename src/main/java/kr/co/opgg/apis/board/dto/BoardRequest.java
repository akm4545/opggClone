package kr.co.opgg.apis.board.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

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
}
