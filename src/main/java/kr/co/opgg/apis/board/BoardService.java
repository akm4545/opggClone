package kr.co.opgg.apis.board;

import kr.co.opgg.apis.board.dto.BoardRequest;
import kr.co.opgg.apis.board.dto.BoardResponse;
import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.datasource.board.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ResponseService responseService;

    public ListResult<List<BoardResponse.BoardList>> selectBoardList(BoardRequest.BoardListSearchCondition searchCondition) {
        return null;
    }
}
