package kr.co.opgg.apis.leaderBoard.dto;

import lombok.Data;

import java.util.List;

public class LeaderBoardResponse {

    @Data
    public static class SelectLeaderBoardListDto{
        private Integer page;

        private Integer externalApiPage;

        private Integer requestCount;

        private String tier;

        private String division;

        private Boolean lastYn;

        private List<LeaderBoardItemDto> LeaderBoardItemList;
    }

    @Data
    public static class LeaderBoardItemDto{

    }
}
