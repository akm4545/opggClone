package kr.co.opgg.apis.statistics;

import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.leader_board.dto.LeaderBoardRequest;
import kr.co.opgg.apis.statistics.dto.StatisticsRequest;
import kr.co.opgg.apis.statistics.dto.StatisticsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {
    private static List<List<String>> qType = new ArrayList<>(); //0번 인덱스는 솔로랭크 참가자들의 데이터 //1번 인덱스는 자랭 참가자들의 데이터
    private static Map<String, ArrayList<String>> tierDataMap = new HashMap<>();

    public ListResult<StatisticsResponse> selectStatistics(StatisticsRequest statisticsRequest) {
        String tier = "";
        String division ="";
        List<String> specificStatistics = new ArrayList<>();

        if(statisticsRequest.getTier() != 0){
            tier = LeaderBoardRequest.LeaderBoardTierAndDivisionDto.getTierList().get(statisticsRequest.getTier());
        }
        if(statisticsRequest.getDivision() != 0){
            division = LeaderBoardRequest.LeaderBoardTierAndDivisionDto.getTierList().get(statisticsRequest.getDivision());
        }

        if(tierDataMap.get(tier) == null) {
            //요청해서 받아온 데이터 맵에 적재(해당 티어)
            //webClient
//            qType.get(statisticsRequest.getQType()).
//            tierDataMap.put(tier, )

        }else{
            //qtype에 맞게 필터링한 데이터를 꺼내서 반환
            //유저 데이터가 제이슨형태로 적재
            specificStatistics.add(tierDataMap.get(tier).get(statisticsRequest.getQType()));
        }
        return null;
    }
}
