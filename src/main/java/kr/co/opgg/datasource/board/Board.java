package kr.co.opgg.datasource.board;

import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.file.File;
import kr.co.opgg.datasource.user.User;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "BOARD")
public class Board extends Date {
    @Id
    @Comment("게시판 인덱스")
    @Column(name = "board_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardIdx;

    @Comment("게시판 제목")
    private String title;

    @Comment("게시판 타입")
    @Column(name = "board_type")
    private String boardType;

    @Comment("게시글 좋아요 수")
    @Column(name = "board_good_count")
    private Integer boardGoodCount;

    @Comment("게시글 조회 수")
    @Column(name = "read_count")
    private Integer readCount;

    @Comment("게시글 싫어요 수")
    @Column(name = "bad_count")
    private Integer badCount;

    @Comment("게시글 내용")
    private String content;

    @Comment("유저")
    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @Comment("댓글")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "board")
    private List<kr.co.opgg.datasource.comment.Comment> comments;

    @Comment("파일")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "board")
    private List<File> files;
}
