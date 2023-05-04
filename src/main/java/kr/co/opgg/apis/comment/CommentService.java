package kr.co.opgg.apis.comment;

import kr.co.opgg.apis.comment.dto.CommentRequest;
import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.board.BoardRepository;
import kr.co.opgg.datasource.comment.Comment;
import kr.co.opgg.datasource.comment.CommentRepository;
import kr.co.opgg.datasource.user.User;
import kr.co.opgg.datasource.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CommentService {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public CommonResult insertComment(CommentRequest.insertComment insertComment) {
        Integer userIdx = 0;
        User user = userRepository.getReferenceById(userIdx);

        Integer boardIdx = insertComment.getBoardIdx();
        Board board = boardRepository.getReferenceById(boardIdx);

        Comment comment = Comment.builder()
                .commentContent(insertComment.getCommentContent())
                .commentParentIdx(insertComment.getParentCommentIdx())
                .user(user)
                .board(board)
                .build();

        commentRepository.save(comment);

        return responseService.getSuccessResult();
    }
}
