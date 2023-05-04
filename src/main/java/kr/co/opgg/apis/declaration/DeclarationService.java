package kr.co.opgg.apis.declaration;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.declaration.dto.DeclarationRequest;
import kr.co.opgg.datasource.declaration.Declaration;
import kr.co.opgg.datasource.declaration.DeclarationRepository;
import kr.co.opgg.datasource.user.User;
import kr.co.opgg.datasource.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DeclarationService {

    @Autowired
    private DeclarationRepository declarationRepository;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private UserRepository userRepository;

    private final String boardType = "BOARD";

    private final String commentType = "COMMENT";

    @Transactional
    public CommonResult declarationBoard(DeclarationRequest.DeclarationBoard board) {
        Integer userIdx = 0;
        User user = userRepository.getReferenceById(userIdx);

        Declaration declaration = Declaration.builder()
                .user(user)
                .declarationContent(board.getDeclarationContent())
                .declarationTargetType(boardType)
                .declarationTargetIdx(board.getBoardIdx())
                .user(user)
                .build();

        declarationRepository.save(declaration);

        return responseService.getSuccessResult();
    }

    public CommonResult declarationComment(DeclarationRequest.DeclarationComment declarationComment) {
        Integer userIdx = 0;
        User user = userRepository.getReferenceById(userIdx);

        Declaration declaration = Declaration.builder()
                .user(user)
                .declarationContent(declarationComment.getDeclarationContent())
                .declarationTargetType(commentType)
                .declarationTargetIdx(declarationComment.getCommentIdx())
                .user(user)
                .build();

        declarationRepository.save(declaration);

        return responseService.getSuccessResult();
    }
}
