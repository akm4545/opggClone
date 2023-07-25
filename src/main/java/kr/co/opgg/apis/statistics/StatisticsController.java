package kr.co.opgg.apis.statistics;

import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.leader_board.dto.LeaderBoardRequest;
import kr.co.opgg.apis.statistics.dto.StatisticsRequest;
import kr.co.opgg.apis.statistics.dto.StatisticsResponse;
import kr.co.opgg.apis.summoner.dto.SummonerRequest;
import kr.co.opgg.common.common_dto.WebClientDto;
import kr.co.opgg.utils.validate.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

//    private final StatisticsService statisticsService;
//
//    @Value("${lol.statistics}")
//    private String statistics;
//
//    @Value("${lol.api_key}")
//    private String apiKey;
//
//    @Value("${lol.challenger_statistics}")
//    private String challengerQueue;
//
//    @Value("${lol.grand_master_statistics}")
//    private String grandMasterQueue;
//
//    @Value("${lol.master_statistics}")
//    private String masterQueue;
//
//    @GetMapping(value = "/list")
//    public ResponseEntity<ListResult<StatisticsResponse>> selectStaticsList(StatisticsRequest statisticsRequest, BindingResult bindingResult){
//        ValidateUtil.validateBindingResult(bindingResult);
//        List<String> tierList = LeaderBoardRequest.LeaderBoardTierAndDivisionDto.getTierList();
//        List<String> divisionList = LeaderBoardRequest.LeaderBoardTierAndDivisionDto.getDivisionList();
//        List<String> highTier = List.of(challengerQueue, grandMasterQueue, masterQueue);
//        Map<String, List<StatisticsResponse.LeagueEntryDto>> tierUser = new HashMap<>();
//        String queue = "RANKED_SOLO_5x5";
//        for(int i=0; i<tierList.size(); i++){
//            WebClientDto.ReqWebClientDto webClientDto = new WebClientDto.ReqWebClientDto();
//            statisticsRequest.setTier(tierList.get(i));
//            if(i > 2){
//                for(String division : divisionList){
//                    webClientDto = setReqParam(statistics, "/" + queue + "/" + tierList.get(i)+"/"+division +"?page=1&" + apiKey, "lowTier");
//                    List<StatisticsResponse.LeagueEntryDto> leagueEntryDtoList = Arrays.asList((StatisticsResponse.LeagueEntryDto[]) statisticsService.selectTier(webClientDto));
//                    tierUser.put(tierList.get(i)+division, leagueEntryDtoList) ;
//                }
//            }else{
//                webClientDto = setReqParam(highTier.get(i), "/" + queue + "?page=1&" + apiKey, "highTier");
//                tierUser.put(tierList.get(i), ((StatisticsResponse.LeagueListDto) statisticsService.selectTier(webClientDto)).getEntries());
//            }
//
//            System.out.println(tierUser);
//
//        }
//
//        //챔피언별 통계 or 티어별 통계
//        //큐타입 솔랭 자랭 칼바람 우르프
//        //리그 => 티어
//        //게임 진행시간
//        //포지션
//        //처음부터 끝까지 챔피언 다보여주기
//
////        ListResult<StatisticsResponse> statistics = statisticsService.selectStatistics(statisticsRequest);
//
//
//
//        return null;
//    }
//
//    public WebClientDto.ReqWebClientDto setReqParam(String baseURL, String reqURL, String reqType){
//        return WebClientDto.ReqWebClientDto.builder()
//                .baseURL(baseURL)
//                .requestURL(reqURL)
//                .reqType(reqType)
//                .build();
//    }
}
