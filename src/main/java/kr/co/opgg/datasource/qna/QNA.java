package kr.co.opgg.datasource.qna;

import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.user.User;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "QNA")
@Builder
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

    @Comment("1:1문의 답변")
    @Column(name = "qna_answer")
    private String qnaAnswer;

    @Comment("유저")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;
}
