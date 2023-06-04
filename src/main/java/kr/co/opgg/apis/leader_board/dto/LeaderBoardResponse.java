package kr.co.opgg.apis.leader_board.dto;

import lombok.Data;

public class LeaderBoardResponse {

    //승률, 모스트챔피언, 레벨 추출 필요
    @Data
    public static class LeaderBoardItemDto{
        private String summonerName;

        private String tier;

        private String leaguePoints;

        private Integer wins;

        private Integer losses;
    }
}
