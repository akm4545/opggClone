package kr.co.opgg.apis.multi_search.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

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
        private InfoDto info;
    }

    @Data
    public static class InfoDto{
        private String gameEndTimestamp;

        private List<ParticipantsDto> participants;
    }

    @Data
    public static class ParticipantsDto{
        private String championName;

        private String championId;

        private String lane;

        private String puuid;

        private Integer kills;

        private Integer assists;

        private Integer deaths;

        private Boolean ace;

        private Double kda;

        private Boolean win;
    }

    @Data
    @Builder
    @ToString
    public static class SelectMultiSearchListDto{
        private String name;

        private String tier;

        private String rank;

        private String leaguePoints;

        private String wins;

        private String losses;

        private String lane;

        private String gameStartDate;

        private List<MultiSearchResponse.ParticipantsDto> gameList;

        private LaneTotalInfoDto laneInfo;

        private String gameEndTimeStamp;
    }

    @Data
    @Builder
    public static class LaneInfoDto{
        private String lane;

        private Integer winCount;
    }

    @Data
    @Builder
    public static class LaneTotalInfoDto{
        private LaneInfoDto lane;

        private LaneInfoDto subLane;
    }
}
