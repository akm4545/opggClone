package kr.co.opgg.apis.board;

import org.springframework.stereotype.Component;

@Component
public class BoardCheck {

    private final String ALL_CATEGORY = "ALL";

    public Boolean isAllCategory (String category) {
        if(category.equals(ALL_CATEGORY)){
            return true;
        }else{
            return false;
        }
    }
}
