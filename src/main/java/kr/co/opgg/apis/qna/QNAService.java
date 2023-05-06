package kr.co.opgg.apis.qna;

import com.mysql.cj.util.StringUtils;
import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.qna.dto.QNARequest;
import kr.co.opgg.common.jwttoken.JwtUtil;
import kr.co.opgg.datasource.qna.QNA;
import kr.co.opgg.datasource.qna.QNARepository;
import kr.co.opgg.datasource.user.User;
import kr.co.opgg.datasource.user.UserRepository;
import kr.co.opgg.utils.user.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.co.opgg.common.exception.CommonException.*;
import static kr.co.opgg.common.exception.QNAException.*;

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

    @Autowired
    private UserUtil userUtil;

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

    @Transactional
    public CommonResult updateQNA(QNARequest.updateQAN updateQna) {
        Integer qnaIdx = updateQna.getQnaIdx();
        QNA qna = qnaRepository.findById(qnaIdx).orElseThrow(() -> DOES_NOT_EXIST_EXCEPTION);
        String answer = qna.getQnaAnswer();

        User user = qna.getUser();
        Integer userIdx = Integer.parseInt(String.valueOf(user.getUserIdx()));

        if(!userUtil.isWriter(userIdx)){
            throw ABNORMAL_ACCESS_EXCEPTION;
        }else if(StringUtils.isNullOrEmpty(answer)){
            throw NOT_UPDATE_EXCEPTION;
        }

        qna.setQnaTitle(updateQna.getQnaTitle());
        qna.setQnaContent(updateQna.getQnaContent());

        return responseService.getSuccessResult();
    }
}
