package kr.co.opgg.apis.declaration;

import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.declaration.dto.DeclarationRequest;
import kr.co.opgg.utils.validate.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DeclarationController {

    @Autowired
    private DeclarationService declarationService;
    @PostMapping("/board/{boardIdx}/declaration")
    public ResponseEntity<CommonResult> declarationBoard(@RequestBody @Valid DeclarationRequest.DeclarationBoard board, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(declarationService.declarationBoard(board));
    }

    @PostMapping("/board/{boardIdx}/comment/{commentIdx}/declaration")
    public ResponseEntity<CommonResult> declarationComment(@RequestBody @Valid DeclarationRequest.DeclarationComment declarationComment, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(declarationService.declarationComment(declarationComment));
    }
}
