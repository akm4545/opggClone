package kr.co.opgg.datasource.comment;

import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.common.Date;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "COMMENT")
public class Comment extends Date {
    @Id
    @org.hibernate.annotations.Comment("댓글 인덱스")
    @Column(name = "comment_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentIdx;

    @org.hibernate.annotations.Comment("댓글 부모 인덱스")
    @Column(name = "comment_parent_idx")
    private Integer commentParentIdx;
    
    @org.hibernate.annotations.Comment("게시글")
    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name = "board_idx")
    private Board board;
}
