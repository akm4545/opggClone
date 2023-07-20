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

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Value("${lol.statistics}")
    private String statistics;

    @Value("${lol.api_key}")
    private String apiKey;

    @GetMapping(value = "/list")
    public ResponseEntity<ListResult<StatisticsResponse>> selectStaticsList(StatisticsRequest statisticsRequest, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);
        List<String> tierList = LeaderBoardRequest.LeaderBoardTierAndDivisionDto.getTierList();
        List<String> divisionList = LeaderBoardRequest.LeaderBoardTierAndDivisionDto.getDivisionList();
        String queue = "RANKED_SOLO_5x5";
        for(int i=0; i<tierList.size(); i++){
            statisticsRequest.setTier(tierList.get(i));
            if(i > 2){
                for(String division : divisionList){
                    setReqParam(statistics, "/" + queue + "/" + tierList.get(i)+"/"+division + "&" + apiKey, "totalTier");
                }
            }else{
                setReqParam(statistics, "/" + queue + "/" + tierList.get(i)+"/"+divisionList.get(0) + "&" + apiKey, "totalTier");
            }
        }

        //챔피언별 통계 or 티어별 통계
        //큐타입 솔랭 자랭 칼바람 우르프
        //리그 => 티어
        //게임 진행시간
        //포지션
        //처음부터 끝까지 챔피언 다보여주기

//        ListResult<StatisticsResponse> statistics = statisticsService.selectStatistics(statisticsRequest);



        return null;
    }

    public WebClientDto.BasicDto setReqParam(String baseURL, String reqURL, String reqType){
        WebClientDto.BasicDto basicDto = WebClientDto.BasicDto.builder()
                .baseURL(baseURL + reqURL).build();
        return basicDto;
    }
}
