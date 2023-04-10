package kr.co.opgg.apis.board;

import kr.co.opgg.apis.board.dto.BoardRequest;
import kr.co.opgg.apis.board.dto.BoardResponse;
import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.common.dto.PageResult;
import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.board.BoardRepository;
import kr.co.opgg.utils.validate.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private BoardCheck boardCheck;

    @Autowired
    private PageUtil pageUtil;

    public PageResult<List<BoardResponse.BoardList>> selectBoardList(BoardRequest.BoardListSearchCondition searchCondition, Pageable pageable) {
        String sort = searchCondition.getSort();
        pageable = pageUtil.getSortPageable(pageable, sort);

        String category = searchCondition.getCategory();
        List<Board> boardList;

        if(boardCheck.isAllCategory(category)){
            boardList = (List<Board>) boardRepository.findAll(pageable).get();
        }else{
            boardList = boardRepository.findAllByBoardType(category, pageable).get();
        }

        return null;
    }
}
