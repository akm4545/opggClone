package kr.co.opgg.apis.statistics;

import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.leader_board.dto.LeaderBoardRequest;
import kr.co.opgg.apis.statistics.dto.StatisticsRequest;
import kr.co.opgg.apis.statistics.dto.StatisticsResponse;
import kr.co.opgg.apis.summoner.dto.SummonerRequest;
import kr.co.opgg.apis.summoner.dto.SummonerResponse;
import kr.co.opgg.common.common_dto.WebClientDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

//    @Value("${lol.statistics}")
//    private String statisticsUrl;
//
//    @Value("${lol.api_key}")
//    private String apiKey;
//
//    private static List<List<SummonerResponse.MatchParticipant>> qType = new ArrayList<>(); //0번 인덱스는 솔로랭크 참가자들의 데이터 //1번 인덱스는 자랭 참가자들의 데이터
//    private static Map<String, ArrayList<String>> tierDataMap = new HashMap<>();
//
//    public ListResult<StatisticsResponse> selectStatistics(StatisticsRequest statisticsRequest) {
//        String tier = "";
//        String division ="";
//        List<String> specificStatistics = new ArrayList<>();
//
//
//        //챌린져 마스터 그마는 디비젼이 1임
//        if(statisticsRequest.getTier() != 0){
//            tier = LeaderBoardRequest.LeaderBoardTierAndDivisionDto.getTierList().get(statisticsRequest.getTier());
//        }
//        if(statisticsRequest.getDivision() != 0){
//            division = LeaderBoardRequest.LeaderBoardTierAndDivisionDto.getDivisionList().get(statisticsRequest.getDivision());
//        }
//
//        String gameType = StatisticsRequest.GameTypeDto.getGameType(statisticsRequest.getQType());
//
//        //몇일 데이터인지도 중요해 하루에 한번정도 요청하면좋을듯?
//        if(tierDataMap.get(tier) == null) {
//            //요청해서 받아온 데이터 맵에 적재(해당 티어)
//            WebClientDto.BasicDto makeWebClient = WebClientDto.BasicDto.builder()
//                    .baseURL(statisticsUrl)
//                    .contentType("application/x-www-form-urlencoded;charset=utf-8")
//                    .build();
//
//            WebClientDto.ReqWebClientDto makeReqWebClient = new WebClientDto.ReqWebClientDto();
//            makeReqWebClient.setBasicDto(makeWebClient);
//            makeReqWebClient.setRequestURL(gameType + "/" + tier + "/" + division +"?page=1" + "&" + apiKey);
//            selectTier(makeReqWebClient);
//
//
//        }else{
//            //qtype에 맞게 필터링한 데이터를 꺼내서 반환
//            //유저 데이터가 제이슨형태로 적재
//
////            specificStatistics.add(tierDataMap.get(tier).get(statisticsRequest.getQType()));
//        }
//        return null;
//    }
//
//    public WebClient statisticsWebClient(WebClientDto.BasicDto webClientDto){
//        return WebClient.builder()
//                .defaultHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
//                .baseUrl(webClientDto.getBaseURL())
//                .build();
//    }
//
//    public String selectTier(WebClientDto.ReqWebClientDto webClientDto){
//        StatisticsResponse.LeagueEntryDto[] block = statisticsWebClient(webClientDto.getBasicDto()).get()
//                .uri(webClientDto.getRequestURL())
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(StatisticsResponse.LeagueEntryDto[].class)
//                .block();
//
//        for(StatisticsResponse.LeagueEntryDto entry : block){
//            System.out.println("entry = " + entry);
//        }
//
//        return null;
//    }
}
