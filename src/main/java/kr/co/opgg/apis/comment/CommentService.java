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
import kr.co.opgg.utils.user.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.co.opgg.common.exception.CommonException.ABNORMAL_ACCESS_EXCEPTION;
import static kr.co.opgg.common.exception.CommonException.DOES_NOT_EXIST_EXCEPTION;

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

    @Autowired
    private UserUtil userUtil;

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

    @Transactional
    public CommonResult updateComment(CommentRequest.updateComment updateComment) {
        Integer commentIdx = updateComment.getCommentIdx();
        Comment comment = commentRepository.findById(commentIdx).orElseThrow(() -> DOES_NOT_EXIST_EXCEPTION);

        Integer userIdx = Integer.parseInt(String.valueOf(comment.getUser().getUserIdx()));

        if(!userUtil.isWriter(userIdx)){
            throw ABNORMAL_ACCESS_EXCEPTION;
        }

        comment.setCommentContent(updateComment.getCommentContent());

        return responseService.getSuccessResult();
    }

    @Transactional
    public CommonResult deleteComment(CommentRequest.deleteComment deleteComment) {
        Integer commentIdx = deleteComment.getCommentIdx();
        Comment comment = commentRepository.findById(commentIdx).orElseThrow(() -> DOES_NOT_EXIST_EXCEPTION);

        Integer userIdx = Integer.parseInt(String.valueOf(comment.getUser().getUserIdx()));

        if(!userUtil.isWriter(userIdx)){
            throw ABNORMAL_ACCESS_EXCEPTION;
        }

        List<Comment> childCommentList = comment.getChildren();

        commentRepository.deleteById(commentIdx);

        if(!childCommentList.isEmpty()){
            commentRepository.deleteAll(childCommentList);
        }

        return responseService.getSuccessResult();
    }
}
