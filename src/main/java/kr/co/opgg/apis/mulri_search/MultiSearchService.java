package kr.co.opgg.apis.mulri_search;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class MultiSearchService {

    public WebClient summonerWebClient(String summonerNameURL) {
        return WebClient.builder()
                .defaultHeader("Content-type" , "application/x-www-form-urlencoded;charset=utf-8")
                .baseUrl(summonerNameURL)
                .build();
    }

    public String[] splitSummonerName(String summonerName){
        List<String> summonerNameList = new ArrayList<String>();

        String[] summonerNameArray = summonerName.split(",");

        if(summonerNameArray.length == 0){
            summonerNameArray = summonerName.split(" 님이 방에 참가했습니다.");
        }

        return summonerNameArray;
    }
}
