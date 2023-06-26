package kr.co.opgg.apis.mulri_search;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.leader_board.dto.LeaderBoardResponse;
import kr.co.opgg.apis.mulri_search.dto.MultiSearchResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class MultiSearchController {

    private final MultiSearchService multiSearchService;

    private final ResponseService responseService;

    @Value("${lol.leaderboard}")
    private String summonerNameURL;

    @Value("${lol.league}")
    private String leagueUrl;

    @Value("${lol.match}")
    private String matchUrl;

    @Value("${lol.api_key}")
    private String apiKey;

    @GetMapping("")
    public ResponseEntity<ListResult> selectMultiSearchList(String summonerName){
        List<MultiSearchResponse.SummonerNameDto> summonerNameDtoList = getSummonerList(summonerName);
        List<MultiSearchResponse.LeagueInfoDto> leagueInfoDtoList = getLeagueInfoList(summonerNameDtoList);

        for(MultiSearchResponse.SummonerNameDto summonerNameDto : summonerNameDtoList){
            List<String> matchIdList = new ArrayList<String>();
            matchIdList = getMatchIdList(summonerNameDto);


            List<MultiSearchResponse.MatchInfoDto> matchInfoDtoList = getMatchInfoList(matchIdList);

            for(int i=0; i<10; i++){
                Integer endIndex = (i + 1) * 10 - 1;

                //10개씩 나눠서 = 1게임
                List<MultiSearchResponse.MatchInfoDto> gameByMatchInfoList = matchInfoDtoList.subList(i, endIndex);


            }


            //10개 kda 계산 킬과 어시스트를 합하고 데스로 나눠 계산합니다.
            //자신의 puuid 찾기 자기 자신이 가장 높다면 ace 추가
        }
        
        //자신의 10 게임의 포지션 평균을 구해 가장 높으 포지션을 주 포지션으로 전달
        
        return ResponseEntity.ok(responseService.getListResult(null));
    }

    private List<MultiSearchResponse.SummonerNameDto> getSummonerList(String summonerName){
        WebClient webClient = multiSearchService.summonerWebClient(summonerNameURL);
        String[] summonerNameArray = multiSearchService.splitSummonerName(summonerName);
        List<MultiSearchResponse.SummonerNameDto> summonerNameDtoList = new ArrayList<MultiSearchResponse.SummonerNameDto>();

        for (String name :summonerNameArray){
            MultiSearchResponse.SummonerNameDto summonerNameDto = (MultiSearchResponse.SummonerNameDto) webClient.get()
                    .uri(name + "?api_key=" + apiKey)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<MultiSearchResponse.SummonerNameDto>() {})
                    .block();

            summonerNameDtoList.add(summonerNameDto);
        }

        return summonerNameDtoList;
    }

    private List<MultiSearchResponse.LeagueInfoDto> getLeagueInfoList(List<MultiSearchResponse.SummonerNameDto> summonerNameDtoList){
        WebClient webClient = multiSearchService.summonerWebClient(leagueUrl);
        List<MultiSearchResponse.LeagueInfoDto> leagueInfoDtoList = new ArrayList<MultiSearchResponse.LeagueInfoDto>();

        for (MultiSearchResponse.SummonerNameDto summoner :summonerNameDtoList){
            MultiSearchResponse.LeagueInfoDto leagueInfoDto = (MultiSearchResponse.LeagueInfoDto) webClient.get()
                    .uri(summoner.getPuuid() + "?api_key=" + apiKey)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<MultiSearchResponse.LeagueInfoDto>() {})
                    .block();

            leagueInfoDtoList.add(leagueInfoDto);
        }

        return leagueInfoDtoList;
    }

    private List<String> getMatchIdList(MultiSearchResponse.SummonerNameDto summonerNameDto){
        WebClient webClient = multiSearchService.summonerWebClient(matchUrl);
        List<String> matchIdList = new ArrayList<String>();

        matchIdList = (List<String>) webClient.get()
                .uri("by-puuid" + summonerNameDto.getPuuid() + "/ids?start=0&count=10&&api_key=" + apiKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                .block();

        return matchIdList;
    }

    private List<MultiSearchResponse.MatchInfoDto> getMatchInfoList(List<String> matchIdList){
        WebClient webClient = multiSearchService.summonerWebClient(matchUrl);
        List<MultiSearchResponse.MatchInfoDto> matchInfoList = new ArrayList<MultiSearchResponse.MatchInfoDto>();

        for(String matchId : matchIdList){
            List<MultiSearchResponse.MatchInfoDto> matchInfoAddList = (List<MultiSearchResponse.MatchInfoDto>) webClient.get()
                    .uri(matchId + "?api_key=" + apiKey)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<MultiSearchResponse.MatchInfoDto>>() {})
                    .block();

            matchInfoList.addAll(matchInfoAddList);
        }

        return matchInfoList;
    }

    private List<MultiSearchResponse.MatchInfoDto> setAce(List<MultiSearchResponse.MatchInfoDto> matchInfoDtoList){
        return matchInfoDtoList;
    }
}
