package kr.co.opgg.apis.multi_search;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.multi_search.dto.MultiSearchResponse;
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
        String gameEndTimeStamp = "";

        List<MultiSearchResponse.SelectMultiSearchListDto> multiSearchList = new ArrayList<MultiSearchResponse.SelectMultiSearchListDto>();

        for(int i=0; i<summonerNameDtoList.size(); i++){
            MultiSearchResponse.SummonerNameDto summonerNameDto = summonerNameDtoList.get(i);
            MultiSearchResponse.LeagueInfoDto leagueInfoDto = leagueInfoDtoList.get(i);

            List<String> matchIdList = new ArrayList<String>();
            matchIdList = getMatchIdList(summonerNameDto);

            List<MultiSearchResponse.MatchInfoDto> matchInfoDtoList = getMatchInfoList(matchIdList);
            List<MultiSearchResponse.MatchInfoDto> matchInfoListByPuuId = new ArrayList<MultiSearchResponse.MatchInfoDto>();

            Integer startIndex = 0;
            Integer endIndex = 0;

            for(int j=0; j<10; j++){
                endIndex = (j + 1) * 10 - 1;

                List<MultiSearchResponse.MatchInfoDto> gameByMatchInfoList = matchInfoDtoList.subList(startIndex, endIndex);

                gameEndTimeStamp = gameByMatchInfoList.get(endIndex).getGameEndTimestamp();
                gameByMatchInfoList = multiSearchService.setAce(gameByMatchInfoList);
                MultiSearchResponse.MatchInfoDto puuIdFilterDto = multiSearchService.puuidFilter(gameByMatchInfoList, summonerNameDto);

                matchInfoListByPuuId.add(puuIdFilterDto);

                startIndex = endIndex + 1;
            }

            MultiSearchResponse.LaneTotalInfoDto lane = multiSearchService.getLane(matchInfoListByPuuId);

            MultiSearchResponse.SelectMultiSearchListDto multiSearchDto = MultiSearchResponse.SelectMultiSearchListDto.builder()
                    .name(summonerNameDto.getName())
                    .rank(leagueInfoDto.getRank())
                    .tier(leagueInfoDto.getTier())
                    .leaguePoints(leagueInfoDto.getLeaguePoints())
                    .wins(leagueInfoDto.getWins())
                    .losses(leagueInfoDto.getLosses())
                    .gameEndTimeStamp(gameEndTimeStamp)
                    .laneInfo(lane)
                    .gameList(matchInfoListByPuuId)
                    .build();

            multiSearchList.add(multiSearchDto);
        }

        return ResponseEntity.ok(responseService.getListResult(multiSearchList));
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
}
