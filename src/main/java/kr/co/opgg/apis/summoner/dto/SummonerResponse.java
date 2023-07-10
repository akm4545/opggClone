package kr.co.opgg.apis.summoner.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Data
public class SummonerResponse {

    @Getter
    @ToString
    public static class SummonerInfo{
        private String accountId;

        private int profileIconId;

        private  Long revisionDate;

        private String name;

        private String id;

        private String puuid;

        private Long summonerLevel;
    }

    @Getter
    @ToString
    public static class MatchInfo{
        @Getter
        private List<MatchParticipant> matchParticipants;
    }

    @Getter
    @ToString
    public static class Match{
//        private MetaData metadata;
        private Info info;
        private Match summonerMatch;
    }

    @Getter
    @ToString
    public static class MetaData{
        private String dataVersion;
        private String matchId;
    }

    @Getter
    @ToString
    public static class Info{
        private List<MatchParticipant> participants;
        private Long gameCreation;
        private Long gameDuration;
        private String gameMode;
        private String gameType;
    }

    @Getter
    @ToString
    public static class MatchParticipant{
        private int championId; // 챔피언 아이디
        private String championName; // 챔피언 이름
        private int deaths; //죽은 수
        private int largestMultiKill; //최대 연속 킬수
        private int kills; //킬수
        private int assists;
        private int item0; //아이템 칸수
        private int item1;
        private int item2;
        private int item3;
        private int item4;
        private int item5;
        private int item6;
        private int participantId; //몇번째 위치인지
        private String summonerName;
        private int teamId;
        private Boolean win;
        private int summonerLevel;
        private int summoner1Id; //스펠
        private int summoner2Id; //스펠
        private String teamPosition;
        private int totalDamageDealt;
        private int wardsKilled;
        private int wardsPlaced;
        private String lane;
    }

    @Getter
    @ToString
    public static class MatchResult{
        private List<Match> matches = new ArrayList<>();

        private List<Map<String, MatchParticipant>> summonerMatches = new ArrayList<>();

        private List<Integer> totalKills = new ArrayList<>();

        @Builder
        public MatchResult(List<Match> matches, List<Map<String, MatchParticipant>> summonerMatches, List<Integer> totalKills){
            this.matches.addAll(matches);
            this.summonerMatches.addAll(summonerMatches);
            this.totalKills.addAll(totalKills);
        }
    }
}
