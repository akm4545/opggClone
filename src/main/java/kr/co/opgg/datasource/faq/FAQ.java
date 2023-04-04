package kr.co.opgg.datasource.faq;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "FAQ")
public class FAQ {
    @Id
    @Comment("자주하는 질문")
    @Column(name = "faq_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer faqIdx;

    @Comment("자주하는 질문 제목")
    private String title;

    @Comment("자주하는 질문 내용")
    @Column(name = "faq_content")
    private String faqContent;

    @Comment("유저 인덱스")
    @Column(name = "user_idx")
    private Integer userIdx;
}
