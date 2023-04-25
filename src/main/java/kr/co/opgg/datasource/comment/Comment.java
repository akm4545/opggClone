package kr.co.opgg.datasource.comment;

import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @org.hibernate.annotations.Comment("댓글")
    @Column(name = "comment_content")
    private String commentContent;

    @org.hibernate.annotations.Comment("유저")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @org.hibernate.annotations.Comment("자식 댓글")
    @OneToMany(mappedBy = "commentParentIdx")
    private List<Comment> children = new ArrayList<Comment>();
}
