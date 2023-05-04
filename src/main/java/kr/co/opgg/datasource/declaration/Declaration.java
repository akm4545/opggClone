package kr.co.opgg.datasource.declaration;

import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.user.User;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "DECLARATION")
public class Declaration extends Date {
    @Id
    @Comment("신고 인덱스")
    @Column(name = "declaration_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer declarationIdx;

    @Comment("신고 대상 인덱스")
    @Column(name = "declaration_target_idx")
    private Integer declarationTargetIdx;

    @Comment("신고 내용")
    @Column(name = "declaration_content")
    private String declarationContent;

    @Comment("신고 대상 유형")
    @Column(name = "declaration_target_type")
    private String declarationTargetType;

    @Comment("신고자")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;
}
