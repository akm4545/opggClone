package kr.co.opgg.apis.statistics.dto;

import kr.co.opgg.apis.leader_board.dto.LeaderBoardRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
public class StatisticsRequest {
        //챔피언별 통계 or 티어별 통계 c or t
        @NotBlank
        private String searchType;

        //큐타입 솔랭 자랭 칼바람 우르프
        @NotBlank
        private int qType;

        @NotBlank
        private int tier;

        @NotBlank
        private int division;

        //게임 진행시간
        @NotBlank
        private String gameTime;

        private String position;
}
