package kr.co.opgg.datasource.qna;

import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "QNA")
public class QNA extends Date {
    @Id
    @Comment("1:1문의 인덱스")
    @Column(name = "qna_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer qnaIdx;

    @Comment("1:1문의 제목")
    @Column(name = "qna_title")
    private String qnaTitle;

    @Comment("1:1문의 내용")
    @Column(name = "qna_content")
    private String qnaContent;

    @Comment("유저")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;
}
