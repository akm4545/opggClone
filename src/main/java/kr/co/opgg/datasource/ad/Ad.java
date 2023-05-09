package kr.co.opgg.datasource.ad;

import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Ad extends Date {

    @Id
    @GeneratedValue
    @Column(name = "AD_IDX")
    private int adIdx;

    @Column(name = "AD_TITLE")
    private String adTitle;

    @Column(name = "AD_LINK")
    private String adLink;

    @Column(name = "AD_STARTDATE")
    private String adStartDate;

    @Column(name = "AD_ENDDATE")
    private String adEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_IDX")
    private User user;

    @Builder
    public Ad (int adIdx, String adTitle, String adLink, User user, String adStartDate, String adEndDate){
        this.adIdx = adIdx;
        this.adTitle = adTitle;
        this.adLink = adLink;
        this.user = user;
        this.adStartDate = adStartDate;
        this.adEndDate = adEndDate;
        if(user != null && user.getAds().contains(this)){
            user.getAds().add(this);
        }
    }
}
