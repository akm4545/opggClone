package kr.co.opgg.apis.comment;

import kr.co.opgg.apis.comment.dto.CommentRequest;
import kr.co.opgg.apis.common.dto.CommonResponse;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.utils.validate.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/board")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<CommonResult> insertComment(@RequestBody @Valid CommentRequest.insertComment insertComment, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(commentService.insertComment(insertComment));
    }

    @PutMapping("/{boardIdx}/comment/{commentIdx}")
    public ResponseEntity<CommonResult> updateComment(@RequestBody @Valid CommentRequest.updateComment updateComment, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(commentService.updateComment(updateComment));
    }
}
