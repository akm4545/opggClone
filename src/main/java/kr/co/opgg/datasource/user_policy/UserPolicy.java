package kr.co.opgg.datasource.user_policy;

import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.policy.Policy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_POLICY")
public class UserPolicy extends Date {
    @Id
    @Comment("유저_정책 인덱스")
    @Column(name = "user_level_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userPolicyIdx;

    @Comment("유저 인덱스")
    @Column(name = "user_idx")
    private Integer userIdx;

    @Comment("정책")
    @OneToOne
    @JoinColumn(name = "policy_idx")
    private Policy policy;
}

