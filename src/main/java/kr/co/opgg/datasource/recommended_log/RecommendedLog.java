package kr.co.opgg.datasource.recommended_log;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "RECOMMENDED_LOG")
public class RecommendedLog {
    @Id
    @Comment("추천 로그 인덱스")
    @Column(name = "recommended_log_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recommendedLogIdx;

    @Comment("유저 인덱스")
    @Column(name = "user_idx")
    private Integer userIdx;

    @Comment("타입")
    private String type;

    @Comment("게시글 인덱스")
    @Column(name = "board_idx")
    private Integer boardIdx;
}
