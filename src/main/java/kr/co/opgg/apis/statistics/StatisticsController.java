package kr.co.opgg.apis.statistics;

import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.statistics.dto.StatisticsRequest;
import kr.co.opgg.apis.statistics.dto.StatisticsResponse;
import kr.co.opgg.utils.validate.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

//    @GetMapping(value = "/list")
//    public ResponseEntity<ListResult<StatisticsResponse>> selectStaticsList(StatisticsRequest statisticsRequest, BindingResult bindingResult){
//        ValidateUtil.validateBindingResult(bindingResult);
//        //챔피언별 통계 or 티어별 통계
//        //큐타입 솔랭 자랭 칼바람 우르프
//        //리그 => 티어
//        //게임 진행시간
//        //포지션
//        //처음부터 끝까지 챔피언 다보여주기
//
//        ListResult<StatisticsResponse> statistics = statisticsService.selectStatistics(statisticsRequest);
//        return null;
//    }
}
