package kr.co.opgg.apis.faq;

import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.faq.dto.FAQRequest;
import kr.co.opgg.apis.faq.dto.FAQResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import kr.co.opgg.utils.validate.ValidateUtil;

@RestController
@RequestMapping("/faq")
public class FAQController {

    @Autowired
    private FAQService faqService;

    @GetMapping("/list")
    public ResponseEntity<ListResult<FAQResponse.FAQ>> selectFaqList(@Valid FAQRequest.FAQCategory faqCategory, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(faqService.selectFaqList(faqCategory));
    }

}
