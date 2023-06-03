package kr.co.opgg.apis.summoner.dto;

import lombok.*;


@NoArgsConstructor
@Data
public class SummonerRequest{

    private String summoner;

    private String baseURL;

    private String requestURL;

    public String reqType;
}
