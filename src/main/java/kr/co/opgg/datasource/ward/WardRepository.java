package kr.co.opgg.datasource.ward;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, Integer> {
//    Optional<Ward> findByUserIdxAndBoardIdx(Integer userIdx, Integer boardIdx);
}
