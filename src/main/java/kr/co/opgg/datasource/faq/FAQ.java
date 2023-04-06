package kr.co.opgg.datasource.faq;

import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.user.User;
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
public class FAQ extends Date {
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

    @Comment("자주하는 질문 카테고리")
    @Column(name = "faq_Category")
    private String faqCategory;

    @Comment("유저")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;
}
