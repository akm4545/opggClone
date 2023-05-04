package kr.co.opgg.datasource.recommended_log;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendedLogRepository extends JpaRepository<RecommendedLog, Integer> {
    Optional<RecommendedLog> findByTargetIdxAndUserIdxAndType(Integer userIdx, Integer boardIdx, String type);

}
