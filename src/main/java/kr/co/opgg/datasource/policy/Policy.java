package kr.co.opgg.datasource.policy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "POLICY")
public class Policy {
    @Id
    @Comment("약관 인덱스")
    @Column(name = "policy_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer policyIdx;

    @Comment("약관 내용")
    @Column(name = "policy_content")
    private String policyContent;

    @Comment("약관 제목")
    @Column(name = "policy_title")
    private String policyTitle;

    @Comment("약관 타입")
    @Column(name = "policy_Type")
    private String policyType;
}
