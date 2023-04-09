package kr.co.opgg.apis.faq;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.ListResult;
import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.apis.faq.dto.FAQRequest;
import kr.co.opgg.apis.faq.dto.FAQResponse;
import kr.co.opgg.common.exception.CommonException;
import kr.co.opgg.datasource.faq.FAQ;
import kr.co.opgg.datasource.faq.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    @Autowired
    private ResponseService responseService;
    
    //공통화 부분 빌더 개선점 찾아보기
    public ListResult<FAQResponse.FAQ> selectFaqList(FAQRequest.FAQCategory faqCategory){
        String category = faqCategory.getCategory();

        List<FAQ> faqList = faqRepository.findAllByFaqCategory(category).orElseThrow(() -> CommonException.DOES_NOT_EXIST_EXCEPTION);

        return responseService.getListResult(faqList.stream()
                .map(faq -> FAQResponse.FAQ.builder()
                        .faqIdx(faq.getFaqIdx())
                        .faqTitle(faq.getTitle())
                        .faqContent(faq.getFaqContent())
                        .build()).collect(Collectors.toList()));
    }

    public SingleResult<FAQResponse.FAQ> selectFaq(FAQRequest.FAQ faqRequest) {
        Integer faqIdx = faqRequest.getFaqIdx();

        FAQ faq = faqRepository.findById(faqIdx).orElseThrow(() -> CommonException.DOES_NOT_EXIST_EXCEPTION);

        return responseService.getSingleResult(FAQResponse.FAQ.builder()
                .faqTitle(faq.getTitle())
                .faqContent(faq.getFaqContent())
                .build()
        );
    }
}
