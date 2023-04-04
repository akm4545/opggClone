package kr.co.opgg.datasource.ward;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "WARD")
public class Ward {
    @Id
    @Comment("와드 인덱스")
    @Column(name = "ward_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wardIdx;

    @Comment("게시글 인덱스")
    @Column(name = "board_idx")
    private Integer boardIdx;

    @Comment("유저 인덱스")
    @Column(name = "user_idx")
    private Integer userIdx;
}
