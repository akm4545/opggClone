package kr.co.opgg.apis.mulri_search.dto;

import lombok.Data;

public class MultiSearchResponse {

    @Data
    public static class SummonerNameDto{
        private String id;

        private String accountId;

        private String puuid;

        private String name;
    }

    @Data
    public static class LeagueInfoDto {
        private String tier;

        private String rank;

        private String leaguePoints;

        private String wins;

        private String losses;
    }

    @Data
    public static class MatchInfoDto{
        private String championName;

        private String championId;

        private String lane;

        private String puuid;

        private Integer kills;

        private Integer assists;

        private Integer deaths;

        private Boolean ace;
    }

    @Data
    public static class SelectMultiSearchListDto{
        private String name;

        private String tier;

        private String rank;

        private String leaguePoints;

        private String wins;

        private String losses;
    }
}
