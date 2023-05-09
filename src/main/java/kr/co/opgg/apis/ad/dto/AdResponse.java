package kr.co.opgg.apis.ad.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdResponse {

    private int adIdx;
    private String adTitle;
    private String adLink;
    private int userIdx;

    @Builder
    public AdResponse(int adIdx, String adTitle, String adLink, int userIdx){
        this.adIdx = adIdx;
        this.adTitle = adTitle;
        this.adLink = adLink;
        this.userIdx = userIdx;
    }
}
