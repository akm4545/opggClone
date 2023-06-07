package kr.co.opgg.apis.summoner;

import kr.co.opgg.apis.summoner.dto.SummonerRequest;
import kr.co.opgg.apis.summoner.dto.SummonerResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Service
public class SummonerService {

    public WebClient summonerWebClient(SummonerRequest summonerRequest){
        return WebClient.builder()
                .defaultHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .baseUrl(summonerRequest.getBaseURL())
                .build();
    }

    public Object selectSummonerMatch(SummonerRequest summonerRequest){
        Map<String, Class> map = Map.of(
                "SummonerInfo", SummonerResponse.SummonerInfo.class,
                "MatchInfo", SummonerResponse.MatchInfo.class,
                "Match", SummonerResponse.Match.class,
                "MatchIdList", String[].class
        );

        return summonerWebClient(summonerRequest).get()
                .uri(summonerRequest.getRequestURL())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(map.get(summonerRequest.getReqType()))
                .block();
    }
}
