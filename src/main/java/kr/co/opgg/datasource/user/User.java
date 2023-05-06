package kr.co.opgg.datasource.user;

import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.datasource.ad.Ad;
import kr.co.opgg.datasource.authority.Authority;
import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.qna.QNA;
import kr.co.opgg.datasource.user_level.UserLevel;
import kr.co.opgg.datasource.user_policy.UserPolicy;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends Date {

    @Id
    @GeneratedValue
    @Column(name = "USER_IDX")
    private Long userIdx;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_PW")
    private String userPw;

    @Column(name = "USER_NICKNAME")
    private String userNickName;

    @Column(name = "USER_POLICY_YN")
    private String userPolicyYn;

    @Column(name = "USER_PHONE")
    private String userPhone;

    @OneToOne
    @JoinColumn(name="AUTHORITY_IDX")
    private Authority authority;

    @Comment("광고")
    @OneToMany(mappedBy = "user")
    private List<Ad> ads = new ArrayList<>();

    @Comment("게시글")
    @OneToMany(mappedBy = "user")
    private List<Board> boards;

    @Comment("1:1문의")
    @OneToMany(mappedBy = "user")
    private List<QNA> qnas;

    @Comment("레벨")
    @OneToOne(mappedBy = "user")
    private UserLevel userLevel;

    @Comment("정책")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userIdx")
    private List<UserPolicy> userPolicies;

    @Builder
    public User (String userId, String userPw, String userPhone, String userNickName, String userPolicyYn, Authority authority, List<Ad> ads){
        this.userId = userId;
        this.userPw = userPw;
        this.userPhone = userPhone;
        this.userNickName = userNickName;
        this.userPolicyYn = userPolicyYn;
        this.authority = authority;
        if(authority.getUser() != this){
            authority.setUser(this);
        }
        this.ads = ads;
    }
}
