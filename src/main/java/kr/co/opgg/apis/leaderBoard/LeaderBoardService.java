package kr.co.opgg.apis.leaderBoard;

import kr.co.opgg.apis.leaderBoard.dto.LeaderBoardRequest;
import kr.co.opgg.apis.leaderBoard.dto.LeaderBoardResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LeaderBoardService {
    public WebClient leaderBoardWebClient(String leaderBoardURL) {
        return WebClient.builder()
                .defaultHeader("Content-type" , "application/x-www-form-urlencoded;charset=utf-8")
                .baseUrl(leaderBoardURL)
                .build();
    }

    public LeaderBoardResponse.SelectLeaderBoardListDto getApiParams(LeaderBoardRequest.SearchLeaderBoardDto searchDto) {

        return null;
    }
}
