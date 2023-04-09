package kr.co.opgg.apis.board.dto;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class BoardListSearchCondition {
        private String sort;

        private String category;

        private Integer page;
    }
}
