package kr.co.opgg.apis.ad.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdResponse {

    private Long adIdx;
    private String adTitle;
    private String adLink;
    private Long userIdx;

    @Builder
    public AdResponse(Long adIdx, String adTitle, String adLink, Long userIdx){
        this.adIdx = adIdx;
        this.adTitle = adTitle;
        this.adLink = adLink;
        this.userIdx = userIdx;
    }
}
