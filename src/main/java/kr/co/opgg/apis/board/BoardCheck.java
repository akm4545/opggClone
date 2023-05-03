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
}
