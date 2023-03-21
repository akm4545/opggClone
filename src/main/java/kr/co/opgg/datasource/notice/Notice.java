package kr.co.opgg.datasource.notice;

import kr.co.opgg.datasource.common.Date;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "test")
public class Notice extends Date {
}
