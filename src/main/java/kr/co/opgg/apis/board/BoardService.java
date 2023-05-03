package kr.co.opgg.apis.board;

import kr.co.opgg.apis.board.dto.BoardRequest;
import kr.co.opgg.apis.board.dto.BoardResponse;
import kr.co.opgg.apis.common.CommonService;
import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.common.dto.PageResult;
import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.board.BoardQueryDsl;
import kr.co.opgg.datasource.board.BoardRepository;
import kr.co.opgg.datasource.comment.Comment;
import kr.co.opgg.datasource.user.User;
import kr.co.opgg.datasource.user.UserRepository;
import kr.co.opgg.utils.pagination.PageUtil;
import kr.co.opgg.utils.date.DateUtil;
import kr.co.opgg.utils.user.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.opgg.common.exception.CommonException.*;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private BoardCheck boardCheck;

    @Autowired
    private PageUtil pageUtil;

    @Autowired
    private BoardQueryDsl boardQueryDsl;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtil userUtil;

    @Value("board.upload.path")
    private String filePath;

    private final String fileType = "BOARD";

    public PageResult<BoardResponse.BoardList> selectBoardList(BoardRequest.BoardListSearchCondition searchCondition, Pageable pageable) {
        String sort = searchCondition.getSort();
        pageable = pageUtil.getSortPageable(pageable, sort);

        String category = searchCondition.getCategory();
        Page<Board> boardList;

        if(boardCheck.isAllCategory(category)){
            boardList = boardRepository.findAll(pageable);
        }else{
            boardList = boardRepository.findAllByBoardType(category, pageable).get();
        }

        return responseService.getPageResult(new PageImpl<>(
                boardList
                        .stream()
                        .map(BoardResponse.BoardList::domainToDto)
                        .collect(Collectors.toList())
                ,pageable
                ,boardList.getTotalElements()
        ));
    }

    public SingleResult<BoardResponse.BoardDetail> selectBoard(BoardRequest.Board board) {
        Integer boardIdx = board.getBoardIdx();

        Board boardDetail = boardRepository.findById(boardIdx).orElseThrow(() -> DOES_NOT_EXIST_EXCEPTION);
        List<Comment> commentList = boardQueryDsl.selectCommentList(boardIdx);

        return responseService.getSingleResult(BoardResponse.BoardDetail.domainToDto(boardDetail, commentList));
    }

    @Transactional
    public CommonResult insertBoard(List<MultipartFile> multipartFileList, BoardRequest.BoardDetail boardDetail){
        //토큰에서 정보 추출해야함
        User user = userRepository.getReferenceById(1);

        Board board = Board
                .builder()
                .boardType(boardDetail.getBoardType())
                .title(boardDetail.getTitle())
                .content(boardDetail.getContent())
                .user(user)
                .build();

        boardRepository.save(board);

        if(multipartFileList != null && multipartFileList.size() != 0){
            commonService.fileUpload(multipartFileList, filePath, board ,fileType);
        }

        return responseService.getSuccessResult();
    }

    @Transactional
    public CommonResult updateBoard(List<MultipartFile> multipartFileList, BoardRequest.BoardDetailUpdate boardDetail){
        Integer boardIdx = boardDetail.getBoardIdx();
        Board board = boardRepository.findById(boardIdx).orElseThrow(() -> DOES_NOT_EXIST_EXCEPTION);

        Integer userIdx = Integer.parseInt(String.valueOf(board.getUser().getUserIdx()));

        if(!userUtil.isWriter(userIdx)){
            throw ABNORMAL_ACCESS_EXCEPTION;
        }

        board.setTitle(boardDetail.getTitle());
        board.setContent(boardDetail.getContent());

        if(multipartFileList != null && multipartFileList.size() != 0){
            commonService.fileUpload(multipartFileList, filePath, board ,fileType);
        }

        List<Integer> removeFileList = boardDetail.getRemoveFileList();

        if(removeFileList != null && removeFileList.size() != 0){
            commonService.fileDelete(removeFileList);
        }

        return responseService.getSuccessResult();
    }
}
