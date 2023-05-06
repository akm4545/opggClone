package kr.co.opgg.datasource.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QNARepository extends JpaRepository<QNA, Integer> {

    Optional<List<QNA>> findByUserIdx(Integer userIdx);
}
