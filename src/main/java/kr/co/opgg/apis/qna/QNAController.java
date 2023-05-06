package kr.co.opgg.apis.qna;

import kr.co.opgg.apis.board.dto.BoardRequest;
import kr.co.opgg.apis.board.dto.BoardResponse;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.common.dto.PageResult;
import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.apis.qna.dto.QNARequest;
import kr.co.opgg.apis.qna.dto.QNAResponse;
import kr.co.opgg.utils.validate.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/QNA")
public class QNAController {

    @Autowired
    private QNAService qnaService;

    @PostMapping("/")
    public ResponseEntity<CommonResult> insertQNA(@RequestBody @Valid QNARequest.InsertQAN qna, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(qnaService.insertQNA(qna));
    }

    @PutMapping("/{qnaIdx}")
    public ResponseEntity<CommonResult> updateQNA(@RequestBody @Valid QNARequest.UpdateQAN qna, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(qnaService.updateQNA(qna));
    }

    //페이징 처리가 필요한지는 사이트 체크 필요
    @GetMapping("")
    public ResponseEntity<ListResult> selectQNAList(){
        return ResponseEntity.ok(qnaService.selectQNAList());
    }

    @GetMapping("/{qnaIdx}")
    public ResponseEntity<SingleResult> selectQNA(@Valid QNARequest.QANIdx qna, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(qnaService.selectQNA(qna));
    }

    @DeleteMapping("/{qnaIdx}")
    public ResponseEntity<CommonResult> deleteQNA(@Valid QNARequest.QANIdx qna, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(qnaService.deleteQAN(qna));
    }
}
