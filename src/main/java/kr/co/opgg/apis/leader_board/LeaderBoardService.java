package kr.co.opgg.apis.leader_board;

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
}
