package kr.co.opgg.apis.board;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.datasource.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ResponseService responseService;

}
