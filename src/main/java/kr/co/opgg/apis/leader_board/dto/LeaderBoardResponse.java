package kr.co.opgg.apis.leader_board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

public class LeaderBoardResponse {

    //승률, 모스트챔피언, 레벨 추출 필요
    @Data
    @ToString
    public static class LeaderBoardItemDto{
        private String summonerName;

        private String tier;

        private String leaguePoints;

        private Integer wins;

        private Integer losses;
    }

    @Data
    @Builder
    @ToString
    public static class LeaderBoardPageDto{
        private Integer page;

        private List<LeaderBoardItemDto> leaderBoardList;
    }
}
