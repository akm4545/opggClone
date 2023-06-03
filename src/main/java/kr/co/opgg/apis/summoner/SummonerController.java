package kr.co.opgg.apis.summoner;

import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.apis.summoner.dto.SummonerRequest;
import kr.co.opgg.apis.summoner.dto.SummonerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static kr.co.opgg.apis.summoner.dto.SummonerResponse.*;


@RestController
@RequestMapping("/summoner")
@RequiredArgsConstructor
public class SummonerController {

    @Value("${lol.summoner}")
    private String summonerURL;

    @Value("${lol.apiKey}")
    private String apiKey;

    @Value("${lol.matches}")
    private String matches;

    @Value("${lol.match}")
    private String match;

    private final SummonerService summonerService;




    @GetMapping("/{summoner}")
    public ResponseEntity<SingleResult<SummonerResponse>> selectSummoner (SummonerRequest summonerRequest) {
//        WebClient webClient = summonerService.summonerWebClient(summonerRequest);
//        SummonerResponse.SummonerInfo summonerResponse = webClient.get()
//                .uri("/" + summonerRequest.getSummoner())
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(SummonerResponse.SummonerInfo.class)
//                .block();



        //서머너 아이디로 puuid 출력 ( 게임 시작시간, 끝나는 시간 필터링 가능) /lol/summoner/v4/summoners/by-account/{encryptedAccountId}

        summonerRequest = setReqParam(summonerURL, summonerRequest.getSummoner() + "?" + apiKey, "SummonerInfo");
        SummonerInfo summonerResponse = (SummonerInfo) summonerService.selectSummonerMatch(summonerRequest);

        if(summonerResponse.getPuuid() != null){
            summonerRequest = setReqParam(matches, summonerResponse.getPuuid() + "/ids" + "?start="+ 0+ "&count="+ 20 + "&" + apiKey, "Match");
            MatchList matches = (MatchList) summonerService.selectSummonerMatch(summonerRequest);
        }

        // puuid로 게임 리스트 출력 페이징 (start, count) /lol/match/v5/matches/by-puuid/{puuid}/ids
        // 매치 아이디로 게임 데이터 출력() /lol/match/v5/matches/{matchId}


//        return ResponseEntity.ok(responseService.getSingleResult(summonerResponse));
        return null;
    }

    public SummonerRequest setReqParam(String baseURL, String reqURL, String reqType){
        SummonerRequest summonerRequest = new SummonerRequest();
        summonerRequest.setBaseURL(baseURL);
        summonerRequest.setRequestURL(reqURL);
        summonerRequest.setReqType(reqType);

        return summonerRequest;
    }
}
