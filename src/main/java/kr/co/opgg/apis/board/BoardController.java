package kr.co.opgg.apis.board;

import kr.co.opgg.apis.board.dto.BoardRequest;
import kr.co.opgg.apis.board.dto.BoardResponse;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.common.dto.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import kr.co.opgg.utils.validate.ValidateUtil;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<PageResult<BoardResponse.BoardList>> selectBoardList(@Valid BoardRequest.BoardListSearchCondition searchCondition, BindingResult bindingResult, Pageable pageable){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(boardService.selectBoardList(searchCondition, pageable));
    }
}
