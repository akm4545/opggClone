package kr.co.opgg.apis.leader_board.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

public class LeaderBoardRequest {

    @Data
    public static class SearchLeaderBoardDto{
        private Integer page;
    }

    @Data
    public static class LeaderBoardTierAndDivisionDto{
        private static List<String> tierList = List.of("CHALLENGER", "GRANDMASTER", "MASTER", "DIAMOND", "PLATINUM", "GOLD", "SILVER", "BRONZE", "IRON");

        private static List<String> divisionList = List.of("I", "II", "III", "IV");

        public static List<String> getTierList(){
            return tierList;
        }

        public static List<String> getDivisionList(){
            return divisionList;
        }
    }

    @Data
    @ToString
    public static class LeaderBoardApiRequestDto{
        private Integer page;

        private Integer requestPage;

        private String tier;

        private String division;
    }
}
