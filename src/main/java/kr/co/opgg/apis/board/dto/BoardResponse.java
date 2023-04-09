package kr.co.opgg.apis.board.dto;

import kr.co.opgg.datasource.user.User;
import lombok.Data;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

public class BoardResponse {

    @Data
    public static class BoardList{
        private Integer boardIdx;

        private String title;

        private Integer boardGoodCount;

        private Integer userLevel;

        private String userNickName;

        private Integer commentCount;

        private String thumbnail;
    }
}
