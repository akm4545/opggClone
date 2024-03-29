package kr.co.opgg.datasource.ward;

import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.user.User;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Data
@Entity
@Table(name = "WARD")
@Builder
public class Ward extends Date {
    @Id
    @Comment("와드 인덱스")
    @Column(name = "ward_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wardIdx;

    @Comment("게시글")
    @OneToOne
    @JoinColumn(name = "board_idx")
    private Board board;

    @Comment("유저")
    @OneToOne
    @JoinColumn(name = "user_idx")
    private User user;
}
