package kr.co.opgg.domain;

import kr.co.opgg.datasource.common.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Authority extends Date {

    @Id
    @GeneratedValue
    @Column(name = "AUTHORITY_IDX")
    private Long authorityIdx;

    private String authorityGrant;

    @OneToOne(mappedBy = "authority")
    private User user;

    @Builder
    public Authority (String authorityGrant){
        this.authorityGrant = authorityGrant;
    }
}
