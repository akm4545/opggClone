package kr.co.opgg.apis.statistics.dto;

import kr.co.opgg.apis.leader_board.dto.LeaderBoardRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Setter
@NoArgsConstructor
public class StatisticsRequest {
        //챔피언별 통계 or 티어별 통계 c or t
        @NotBlank
        private String searchType;

        //큐타입 솔랭 자랭 칼바람 우르프
        @NotBlank
        private int qType;

        @NotBlank
        private String tier;

        @NotBlank
        private String division;

        //게임 진행시간
        @NotBlank
        private String gameTime;

        private String position;

        private String baseURL;

        private String requestURL;

        private String reqType;

        public static class GameTypeDto {

                private static List<String> gameType = List.of("RANKED_SOLO_5x5", "RANKED_FLEX_SR");

                public static String getGameType(int qtype){
                        return gameType.get(qtype);
                }
        }
}
