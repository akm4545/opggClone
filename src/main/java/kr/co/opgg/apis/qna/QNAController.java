package kr.co.opgg.apis.qna;

import kr.co.opgg.apis.board.dto.BoardRequest;
import kr.co.opgg.apis.board.dto.BoardResponse;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.common.dto.PageResult;
import kr.co.opgg.apis.qna.dto.QNARequest;
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
    public ResponseEntity<CommonResult> insertQNA(@Valid QNARequest.insertQAN qna, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(qnaService.insertQNA(qna));
    }

    @PutMapping("/{qnaIdx}")
    public ResponseEntity<CommonResult> updateQNA(@Valid QNARequest.updateQAN qna, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(qnaService.updateQNA(qna));
    }
}
