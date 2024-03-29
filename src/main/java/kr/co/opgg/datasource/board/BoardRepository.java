package kr.co.opgg.datasource.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Optional<Page<Board>> findAllByBoardType(String boardType , Pageable pageable);
}
