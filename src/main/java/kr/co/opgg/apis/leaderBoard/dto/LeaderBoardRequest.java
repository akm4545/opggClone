package kr.co.opgg.apis.leaderBoard.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoardRequest {

    @Data
    public static class SearchLeaderBoardDto{
        private Integer page;
    }

    @Data
    public static class leaderBoardTierAndDivisionDto{
        private List<String> tierList = List.of("CHALLENGER", "GRANDMASTER", "MASTER", "DIAMOND", "PLATINUM", "GOLD", "SILVER", "BRONZE", "IRON");

        private List<String> divisionList = List.of("I", "II", "III", "IV");
    }
}
