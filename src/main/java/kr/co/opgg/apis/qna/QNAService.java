package kr.co.opgg.apis.qna;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.qna.dto.QNARequest;
import kr.co.opgg.common.jwttoken.JwtUtil;
import kr.co.opgg.datasource.qna.QNA;
import kr.co.opgg.datasource.qna.QNARepository;
import kr.co.opgg.datasource.user.User;
import kr.co.opgg.datasource.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class QNAService {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private QNARepository qnaRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CommonResult insertQNA(QNARequest.insertQAN insertQna) {
        Integer userIdx = Integer.parseInt(String.valueOf(jwtUtil.getUserIdx()));
        User user = userRepository.getReferenceById(userIdx);

        QNA qna = QNA.builder()
                .qnaTitle(insertQna.getQnaTitle())
                .qnaContent(insertQna.getQnaContent())
                .user(user)
                .build();

        qnaRepository.save(qna);

        return responseService.getSuccessResult();
    }
}
