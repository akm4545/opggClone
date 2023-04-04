package kr.co.opgg.datasource.declaration;

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
public class Declaration {
    @Id
    @Comment("신고 인덱스")
    @Column(name = "declaration_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer declarationIdx;

    @Comment("신고 대상 인덱스")
    @Column(name = "declaration_target_idx")
    private Integer declarationTargetIdx;

    @Comment("신고 대상 유형")
    @Column(name = "declaration_target_type")
    private Integer declarationTargetType;
}
