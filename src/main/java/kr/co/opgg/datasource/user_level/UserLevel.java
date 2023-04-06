package kr.co.opgg.datasource.user_level;

import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.level.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_LEVEL")
public class UserLevel extends Date {
    @Id
    @Comment("유저 레벨 인덱스")
    @Column(name = "user_level_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userLevelIdx;

    @Comment("유저 레벨 경험치")
    @Column(name = "user_level_exp")
    private Integer userLevelExp;

    @Comment("유저 인덱스")
    @Column(name = "user_idx")
    private Integer userIdx;

    @Comment("레벨")
    @OneToOne
    @JoinColumn(name = "level_idx")
    private Level level;
}
