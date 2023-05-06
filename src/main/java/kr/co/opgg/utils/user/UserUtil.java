package kr.co.opgg.utils.user;

import kr.co.opgg.common.jwttoken.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    @Autowired
    private JwtUtil jwtUtil;
    public Boolean isWriter(Integer userIdx){
        Integer loginUserIdx = Integer.parseInt(String.valueOf(jwtUtil.getUserIdx()));

        if(userIdx.equals(loginUserIdx)){
            return true;
        }

        return false;
    }
}
