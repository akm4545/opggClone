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
    private Long idx;

    @Column(name = "AD_TITLE")
    private String adTitle;

    @Column(name = "AD_LINK")
    private String adLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_IDX")
    private User user;

    @Builder
    public Ad (Long idx, String adTitle, String adLink, User user){
        this.idx = idx;
        this.adTitle = adTitle;
        this.adLink = adLink;
        if(user != null && user.getAds().contains(this)){
            user.getAds().add(this);
        }
    }
}
