package kr.co.opgg.datasource.level_policy;

import kr.co.opgg.datasource.common.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LEVEL_POLICY")
public class LevelPolicy extends Date {
    @Id
    @Comment("레벨 정책 인덱스")
    @Column(name = "level_policy_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer levelPolicyIdx;

    @Comment("레벨 정책 타입")
    @Column(name = "level_policy_type")
    private String levelPolicyType;

    @Comment("레벨 정책 경험치")
    @Column(name = "level_policy_exp")
    private Integer levelPolicyExp;
}
