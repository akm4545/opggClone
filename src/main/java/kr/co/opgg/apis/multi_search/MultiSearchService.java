package kr.co.opgg.apis.multi_search;

import kr.co.opgg.apis.multi_search.dto.MultiSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
public class MultiSearchService {

    public WebClient summonerWebClient(String summonerNameURL) {
        return WebClient.builder()
                .defaultHeader("Content-type" , "application/x-www-form-urlencoded;charset=utf-8")
                .baseUrl(summonerNameURL)
                .build();
    }

    public String[] splitSummonerName(String summonerName){
        String[] summonerNameArray = summonerName.split(",");

        if(summonerNameArray.length == 0){
            summonerNameArray = summonerName.split(" 님이 방에 참가했습니다.");
        }

        return summonerNameArray;
    }

    public List<MultiSearchResponse.MatchInfoDto> setAce(List<MultiSearchResponse.MatchInfoDto> matchInfoDtoList){
        for(int i=0; i<matchInfoDtoList.size(); i++){
            MultiSearchResponse.MatchInfoDto matchInfoDto = matchInfoDtoList.get(i);

            Integer kill = matchInfoDto.getKills();
            Integer death = matchInfoDto.getDeaths();
            Integer assists = matchInfoDto.getAssists();

            Double kda = ((kill + assists) / death + 0d);

            matchInfoDto.setKda(kda);
        }

        matchInfoDtoList.stream().sorted(Comparator.comparing(MultiSearchResponse.MatchInfoDto::getKda));
        matchInfoDtoList.get(matchInfoDtoList.size() - 1).setAce(true);

        return matchInfoDtoList;
    }

    public MultiSearchResponse.LaneTotalInfoDto getLane(List<MultiSearchResponse.MatchInfoDto> matchInfoListByPuuId) {
        Map<String, Integer> laneMap = new HashMap<String, Integer>();

        matchInfoListByPuuId.forEach(matchInfoDto -> {
            String lane = matchInfoDto.getLane();
            String laneWinCountKey = lane + "winCount";
            Integer count = 1;
            Integer winCount = 0;

            if(matchInfoDto.getWin()){
                winCount = 1;
            }

            if(laneMap.containsKey(lane)){
                count = laneMap.get(lane);
                winCount = laneMap.get(laneWinCountKey) + winCount;
                count = count + 1;
            }

            laneMap.put(lane, count);
            laneMap.put(laneWinCountKey, winCount);
        });

        MultiSearchResponse.LaneInfoDto laneInfoDto = maxCountLaneSearch(laneMap);
        laneMap.remove(laneInfoDto.getLane());
        MultiSearchResponse.LaneInfoDto subLaneInfoDto = maxCountLaneSearch(laneMap);

        return MultiSearchResponse.LaneTotalInfoDto.builder()
                .lane(laneInfoDto)
                .subLane(subLaneInfoDto)
                .build();
    }

    public MultiSearchResponse.MatchInfoDto puuidFilter(List<MultiSearchResponse.MatchInfoDto> gameByMatchInfoList, MultiSearchResponse.SummonerNameDto summonerNameDto) {
        String puuId = summonerNameDto.getPuuid();

        return gameByMatchInfoList.stream().filter(match -> {
            return match.getPuuid().equals(puuId);
        }).findFirst().get();
    }

    private MultiSearchResponse.LaneInfoDto maxCountLaneSearch(Map<String, Integer> laneMap){
        Set<String> keySet = laneMap.keySet();
        Integer maxCount = 0;
        Integer winCount = 0;
        String lane = "";

        for (String key : keySet){
            String laneWinCountKey = key + "winCount";
            Integer laneCount = laneMap.get(key);

            if(maxCount < laneCount){
                maxCount = laneCount;
                winCount = laneMap.get(laneWinCountKey);
                lane = key;
            }
        };

        return MultiSearchResponse.LaneInfoDto.builder()
                .lane(lane)
                .winCount(winCount)
                .build();
    }
}
