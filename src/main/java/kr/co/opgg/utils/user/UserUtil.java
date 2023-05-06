package kr.co.opgg.utils.user;

import kr.co.opgg.common.jwttoken.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static kr.co.opgg.common.exception.CommonException.*;

@Component
public class UserUtil {

    @Autowired
    private JwtUtil jwtUtil;
    public void isWriter(Integer userIdx){
        Integer loginUserIdx = Integer.parseInt(String.valueOf(jwtUtil.getUserIdx()));

        if(userIdx.equals(loginUserIdx)){
            throw ABNORMAL_ACCESS_EXCEPTION;
        }
    }
}
