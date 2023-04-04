package kr.co.opgg.datasource.level;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LEVEL")
public class Level {
    @Id
    @Comment("레벨 인덱스")
    @Column(name = "level_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer levelIdx;

    @Comment("레벨")
    @Column(name = "level_level")
    private Integer levelLevel;

    @Comment("경험치")
    @Column(name = "level_exp")
    private Integer levelExp;
}
