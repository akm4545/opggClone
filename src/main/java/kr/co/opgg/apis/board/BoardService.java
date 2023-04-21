package kr.co.opgg.apis.board;

import kr.co.opgg.apis.board.dto.BoardRequest;
import kr.co.opgg.apis.board.dto.BoardResponse;
import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.PageResult;
import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.common.exception.CommonException;
import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.board.BoardQueryDsl;
import kr.co.opgg.datasource.board.BoardRepository;
import kr.co.opgg.datasource.comment.Comment;
import kr.co.opgg.utils.pagination.PageUtil;
import kr.co.opgg.utils.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private BoardCheck boardCheck;

    @Autowired
    private DateUtil timeUtil;

    @Autowired
    private PageUtil pageUtil;

    @Autowired
    private BoardQueryDsl boardQueryDsl;

    public PageResult<BoardResponse.BoardList> selectBoardList(BoardRequest.BoardListSearchCondition searchCondition, Pageable pageable) {
        String sort = searchCondition.getSort();
        pageable = pageUtil.getSortPageable(pageable, sort);

        String category = searchCondition.getCategory();
        Page<Board> boardList;

        if(boardCheck.isAllCategory(category)){
            boardList = boardRepository.findAll(pageable);
        }else{
            boardList = boardRepository.findAllByBoardType(category, pageable).get();
        }

        return responseService.getPageResult(new PageImpl<>(
                boardList
                        .stream()
                        .map(BoardResponse.BoardList::domainToDto)
                        .collect(Collectors.toList())
                ,pageable
                ,boardList.getTotalElements()
        ));
    }

    public SingleResult<BoardResponse.BoardDetail> selectBoard(BoardRequest.Board board) {
        Integer boardIdx = board.getBoardIdx();

        Board boardDetail = boardRepository.findById(boardIdx).orElseThrow(() -> CommonException.DOES_NOT_EXIST_EXCEPTION);
        List<Comment> commentList = boardQueryDsl.selectCommentList(boardIdx);

        return responseService.getSingleResult(BoardResponse.BoardDetail.domainToDto(boardDetail, commentList));
    }
}
