package kr.co.opgg.apis.board.dto;

import kr.co.opgg.datasource.board.Board;
import kr.co.opgg.datasource.user.User;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

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
}
