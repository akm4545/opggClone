package kr.co.opgg.domain;

import kr.co.opgg.datasource.common.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne
    @JoinColumn(name="AUTHORITY_IDX")
    private Authority authority;

    @OneToMany(mappedBy = "user")
    private List<Ad> ads = new ArrayList<>();

    @Builder
    public User (String userId, String userPw, String userNickName, String userPolicyYn, Authority authority){
        this.userId = userId;
        this.userPw = userPw;
        this.userNickName = userNickName;
        this.userPolicyYn = userPolicyYn;
        this.authority = authority;
    }
}
