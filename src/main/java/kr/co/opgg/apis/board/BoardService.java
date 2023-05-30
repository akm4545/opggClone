package kr.co.opgg.apis.board;

import kr.co.opgg.apis.board.dto.BoardRequest;
import kr.co.opgg.apis.board.dto.BoardResponse;
import kr.co.opgg.apis.common.CommonService;
import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.common.dto.PageResult;
import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.common.jwttoken.JwtUtil;
import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.board.BoardQueryDsl;
import kr.co.opgg.datasource.board.BoardRepository;
import kr.co.opgg.datasource.comment.Comment;
import kr.co.opgg.datasource.file.File;
import kr.co.opgg.datasource.recommended_log.RecommendedLog;
import kr.co.opgg.datasource.recommended_log.RecommendedLogRepository;
import kr.co.opgg.datasource.user.User;
import kr.co.opgg.datasource.user.UserRepository;
import kr.co.opgg.datasource.ward.Ward;
import kr.co.opgg.datasource.ward.WardRepository;
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

    @Autowired
    private RecommendedLogRepository recommendedLogRepository;

    @Autowired
    private WardRepository wardRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("board.upload.path")
    private String filePath;

    private final String type = "BOARD";

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
        Integer userIdx = Integer.parseInt(String.valueOf(jwtUtil.getUserIdx()));
        User user = userRepository.getReferenceById(userIdx);

        Board board = Board
                .builder()
                .boardType(boardDetail.getBoardType())
                .title(boardDetail.getTitle())
                .content(boardDetail.getContent())
                .user(user)
                .build();

        boardRepository.save(board);

        if(multipartFileList != null && multipartFileList.size() != 0){
            commonService.fileUpload(multipartFileList, filePath, board ,type);
        }

        return responseService.getSuccessResult();
    }

    @Transactional
    public CommonResult updateBoard(List<MultipartFile> multipartFileList, BoardRequest.BoardDetailUpdate boardDetail){
        Integer boardIdx = boardDetail.getBoardIdx();
        Board board = boardRepository.findById(boardIdx).orElseThrow(() -> DOES_NOT_EXIST_EXCEPTION);

        Integer userIdx = Integer.parseInt(String.valueOf(board.getUser().getUserIdx()));

        userUtil.isWriter(userIdx);

        board.setTitle(boardDetail.getTitle());
        board.setContent(boardDetail.getContent());

        if(multipartFileList != null && multipartFileList.size() != 0){
            commonService.fileUpload(multipartFileList, filePath, board ,type);
        }

        List<Integer> removeFileList = boardDetail.getRemoveFileList();

        if(removeFileList != null && removeFileList.size() != 0){
            commonService.fileDelete(removeFileList);
        }

        return responseService.getSuccessResult();
    }

    @Transactional
    public CommonResult deleteBoard(BoardRequest.Board deleteBoard){
        Integer boardIdx = deleteBoard.getBoardIdx();
        Board board = boardRepository.findById(boardIdx).orElseThrow(() -> DOES_NOT_EXIST_EXCEPTION);

        Integer userIdx = Integer.parseInt(String.valueOf(board.getUser().getUserIdx()));

        userUtil.isWriter(userIdx);

        List<File> deleteFileList = board.getFiles();
        List<Integer> deleteFileIdxList = deleteFileList.stream().map(File::getFileIdx).collect(Collectors.toList());

        commonService.fileDelete(deleteFileIdxList);
        boardRepository.delete(board);

        return responseService.getSuccessResult();
    }

    @Transactional
    public CommonResult recommend(BoardRequest.Board board) {
        Integer userIdx = Integer.parseInt(String.valueOf(jwtUtil.getUserIdx()));
        Integer boardIdx = board.getBoardIdx();

        RecommendedLog recommendedLog = recommendedLogRepository.findByTargetIdxAndUserIdxAndType(userIdx, boardIdx, type).get();

        if(recommendedLog == null){
            recommendedLog = RecommendedLog.builder()
                    .targetIdx(boardIdx)
                    .type(type)
                    .userIdx(userIdx)
                    .build();

            recommendedLogRepository.save(recommendedLog);
        }else{
            recommendedLogRepository.delete(recommendedLog);
        }

        return responseService.getSuccessResult();
    }

    @Transactional
    public CommonResult boardWard(BoardRequest.Board boardWard) {
        Integer userIdx = Integer.parseInt(String.valueOf(jwtUtil.getUserIdx()));
        Integer boardIdx = boardWard.getBoardIdx();

//        Ward ward = wardRepository.findByUserIdxAndBoardIdx(userIdx, boardIdx).get();

//        if(ward == null){
//            Board board = boardRepository.getReferenceById(boardIdx);
//            User user = userRepository.getReferenceById(userIdx);
//
//            ward = ward.builder()
//                    .board(board)
//                    .user(user)
//                    .build();
//
//            wardRepository.save(ward);
//        }else{
//            wardRepository.delete(ward);
//        }

        return responseService.getSuccessResult();
    }
}
