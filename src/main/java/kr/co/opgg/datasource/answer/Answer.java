package kr.co.opgg.datasource.answer;

import kr.co.opgg.datasource.common.Date;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ANSWER")
public class Answer extends Date {
    @Id
    @Comment("답변 인덱스")
    @Column(name = "answer_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerIdx;


    @Comment("답변 내용")
    @Column(name = "answer_content")
    private String answerContent;

    @Comment("질문 인덱스")
    @Column(name = "qna_idx")
    private Integer qnaIdx;

    @Comment("유저 인덱스")
    @Column(name = "user_idx")
    private Integer userIdx;
}
