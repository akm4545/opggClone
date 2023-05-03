package kr.co.opgg.apis.board;

import kr.co.opgg.apis.board.dto.BoardRequest;
import kr.co.opgg.apis.board.dto.BoardResponse;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.common.dto.PageResult;
import kr.co.opgg.apis.common.dto.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import kr.co.opgg.utils.validate.ValidateUtil;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/{boardIdx}")
    public ResponseEntity<SingleResult<BoardResponse.BoardDetail>> selectBoard(@Valid BoardRequest.Board board, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(boardService.selectBoard(board));
    }

    @PostMapping("/")
    public ResponseEntity<CommonResult> insertBoard(List<MultipartFile> multipartFileList, @Valid BoardRequest.BoardDetail board, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(boardService.insertBoard(multipartFileList, board));
    }

    @PutMapping("/{boardIdx}")
    public ResponseEntity<CommonResult> updateBoard(List<MultipartFile> multipartFileList, @Valid BoardRequest.BoardDetailUpdate board, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(boardService.updateBoard(multipartFileList, board));
    }
}
