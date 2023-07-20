package kr.co.opgg.apis.statistics.dto;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
public class StatisticsResponse {
    private LeagueListDto leagueListDto;

    @Data
    public static class LeagueListDto{
        private String leagueId;
        private List<LeagueEntryDto> entries;
        private String tier;
        private String name;
        private String queue;

    }
    @Data
    public static class LeagueEntryDto{
        private String leagueId;
        private String summonerId;
        private String summonerName;
        private String queueType;
        private String tier;
        private String rank;
        private int leaguePoints;
        private int wins;
        private int losses;
        private Boolean hotStreak;
        private boolean veteran;
        private boolean freshBlood;
        private boolean inactive;
        private MiniSeriesDto miniSeriesDto;
    }

    @Data
    public static class MiniSeriesDto{
        private int losses;
        private String progress;
        private int target;
        private int win;
    }
}
