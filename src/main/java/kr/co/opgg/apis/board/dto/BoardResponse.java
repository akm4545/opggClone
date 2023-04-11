package kr.co.opgg.apis.board.dto;

import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.comment.Comment;
import kr.co.opgg.datasource.file.File;
import kr.co.opgg.datasource.user.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

public class BoardResponse {

    @Data
    @Builder
    public static class BoardList{
        private Integer boardIdx;

        private String title;

        private Integer boardGoodCount;

        private Integer userLevel;

        private String userNickName;

        private Integer commentCount;

        private String thumbnail;

        public static BoardList domainToDto(Board board){
            return BoardList.builder()
                    .boardIdx(board.getBoardIdx())
                    .title(board.getTitle())
                    .boardGoodCount(board.getBoardGoodCount())
                    .userLevel(board.getUser().getUserLevel().getLevel().getLevelLevel())
                    .userNickName(board.getUser().getUserNickName())
                    .commentCount(board.getComments().size())
                    .thumbnail(board.getFiles() != null ? "Y" : "N")
                    .build();
        }
    }

    @Data
    @Builder
    public static class BoardDetail{
        private Integer boardIdx;

        private String title;

        private String content;

        private String boardType;

        private String createDate;

        private Integer level;

        private String nickName;

        private Integer readCount;

        private Integer commentCount;

        private Integer boardGoodCount;

        private Integer badCount;

        private List<BoardFile> fileList;

        private List<CommentDetail> commentList;
        public static BoardDetail domainToDto(Board board, List<File> fileList, List<CommentDetail> commentList){
            return BoardDetail
                    .builder()
                    .boardIdx(board.getBoardIdx())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .boardType(board.getBoardType())
                    .createDate(board.getCreateDate().toString())
                    .level(board.getUser().getUserLevel().getLevel().getLevelLevel())
                    .nickName(board.getUser().getUserNickName())
                    .readCount(board.getReadCount())
                    .commentCount(commentList.size())
                    .boardGoodCount(board.getBoardGoodCount())
                    .badCount(board.getBadCount())
                    .fileList(fileList.stream().map(BoardFile::domainToDto).collect(Collectors.toList()))
                    .commentList(commentList)
                    .build();
        }
    }

    @Data
    @Builder
    public static class BoardFile{
        private String fileName;

        private String fileDirectory;

        public static BoardFile domainToDto(File file){
            return BoardFile
                    .builder()
                    .fileName(file.getFileName())
                    .fileDirectory(file.getFileDirectory())
                    .build();
        }
    }

    @Data
    @Builder
    public static class CommentDetail{
        private Integer commentIdx;

        private Integer level;

        private String nickName;

        private String createDate;

        private String commentContent;

        private List<Comment> commentList;

        public static CommentDetail domainToDto(Comment comment){
            return CommentDetail
                    .builder()
                    .commentIdx(comment.getCommentIdx())
                    .level(comment.getUser().getUserLevel().getLevel().getLevelLevel())
                    .nickName(comment.getUser().getUserNickName())
                    .createDate(comment.getCreateDate().toString())
                    .commentContent(comment.getCommentContent())
                    .build();
        }
    }
}
