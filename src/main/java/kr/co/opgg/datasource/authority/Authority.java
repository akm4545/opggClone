package kr.co.opgg.datasource.authority;

import kr.co.opgg.datasource.common.Date;
import kr.co.opgg.datasource.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority extends Date {

    @Id
    @GeneratedValue
    @Column(name = "AUTHORITY_IDX")
    private int authorityIdx;

    private String authorityGrant;

    @OneToOne(mappedBy = "authority")
    private User user;

    @Builder
    public Authority (String authorityGrant, User user){
        this.authorityGrant = authorityGrant;
    }
}
