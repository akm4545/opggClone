package kr.co.opgg.apis.board;

import kr.co.opgg.datasource.comment.Comment;
import org.springframework.stereotype.Component;

@Component
public class BoardCheck {

    private final String ALL_CATEGORY = "ALL";

    private final Integer DISPLAY_MINUTES_CONDITION = 1;

    public Boolean isAllCategory (String category) {
        if(category.equals(ALL_CATEGORY)){
            return true;
        }else{
            return false;
        }
    }

    public Boolean isChildComment(Comment comment){
        if(comment.getCommentParentIdx() != null){
            return true;
        }else{
            return false;
        }
    }
    public Boolean isParentComment(Comment comment, Comment childComment){
        if(comment.getCommentIdx().equals(childComment.getCommentParentIdx())){
            return true;
        }else{
            return false;
        }
    }
}
