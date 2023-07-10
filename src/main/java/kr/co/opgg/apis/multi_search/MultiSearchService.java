package kr.co.opgg.apis.multi_search;

import kr.co.opgg.apis.multi_search.dto.MultiSearchResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<MultiSearchResponse.ParticipantsDto> setAce(List<MultiSearchResponse.ParticipantsDto> participantsDtoList){
        for(int i=0; i<participantsDtoList.size(); i++){
            MultiSearchResponse.ParticipantsDto participantsDto = participantsDtoList.get(i);

            Integer kill = participantsDto.getKills();
            Integer death = participantsDto.getDeaths();
            Integer assists = participantsDto.getAssists();

            Double kda = 0D;

            if(death == 0){
                kda = kill + assists + 0D;
            }else{
                kda = ((kill + assists) / (death + 0d));
            }

            participantsDto.setKda(kda);
        }

        participantsDtoList = participantsDtoList.stream().sorted(Comparator.comparing(MultiSearchResponse.ParticipantsDto::getKda)).collect(Collectors.toList());
        participantsDtoList.get(participantsDtoList.size() - 1).setAce(true);

        return participantsDtoList;
    }

    public MultiSearchResponse.LaneTotalInfoDto getLane(List<MultiSearchResponse.ParticipantsDto> matchInfoListByPuuId) {
        Map<String, Integer> laneMap = new HashMap<String, Integer>();

        matchInfoListByPuuId.forEach(matchInfoDto -> {
            String lane = matchInfoDto.getLane();
            String laneWinCountKey = lane + "winCount";
            Integer count = 1;
            Integer winCount = 0;

            System.out.println(matchInfoDto.getWin());
            System.out.println(matchInfoDto.getLane());

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

        System.out.println(laneMap);

        MultiSearchResponse.LaneInfoDto laneInfoDto = maxCountLaneSearch(laneMap);
        laneMap.remove(laneInfoDto.getLane());
        laneMap.remove(laneInfoDto.getLane() + "winCount");
        MultiSearchResponse.LaneInfoDto subLaneInfoDto = maxCountLaneSearch(laneMap);

        return MultiSearchResponse.LaneTotalInfoDto.builder()
                .lane(laneInfoDto)
                .subLane(subLaneInfoDto)
                .build();
    }

    public MultiSearchResponse.ParticipantsDto puuidFilter(List<MultiSearchResponse.ParticipantsDto> gameByMatchInfoList, MultiSearchResponse.SummonerNameDto summonerNameDto) {
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
            if(key.contains("winCount")){
                continue;
            }

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
                .playCount(maxCount)
                .build();
    }
}
