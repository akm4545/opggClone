package kr.co.opgg.utils.user;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {
    public Boolean isWriter(Integer userIdx){
        //Integer 유저인덱스 불러오는 메서드 만들어줘
        Integer testIdx = 0;

        if(userIdx.equals(testIdx)){
            return true;
        }

        return false;
    }
}
