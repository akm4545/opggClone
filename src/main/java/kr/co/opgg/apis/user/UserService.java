package kr.co.opgg.apis.user;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.apis.user.dto.UserResponse;
import kr.co.opgg.common.exception.UserException;
import kr.co.opgg.common.jwttoken.JwtUtil;
import kr.co.opgg.datasource.user.User;
import kr.co.opgg.datasource.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static kr.co.opgg.common.exception.CommonException.ABNORMAL_ACCESS_EXCEPTION;
import static kr.co.opgg.common.exception.CommonException.DOES_NOT_EXIST_EXCEPTION;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    private final ResponseService responseService;

    private final JwtUtil jwtUtil;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public SingleResult<UserResponse> insertUser(UserRequest userRequest) {
        UserResponse existUser = userRepository.findByUserId(userRequest.getUserId());
        if(StringUtils.hasText(existUser.getUserId())){
            throw new UserException.UserExistException();
        }

        User joinUser = User.builder()
                .userId(userRequest.getUserId())
                .userPw(userRequest.getUserPw())
                .userPhone(userRequest.getUserPhone())
                .userNickName(userRequest.getUserNickName())
                .userPolicyYn(userRequest.getUserPolicyYN())
                .build();

        joinUser = userRepository.save(joinUser);
        UserResponse userResponse = UserResponse
                .builder()
                .userId(joinUser.getUserId())
                .userIdx(joinUser.getUserIdx())
                .build();

        return responseService.getSingleResult(userResponse);
    }

    public SingleResult<UserResponse> findUserId(UserRequest.UserPrivateRequest userRequest) {
        return responseService.getSingleResult(userRepository.findUserIdByPhone(userRequest));
    }

    public CommonResult loginUser(UserRequest userRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userRequest.getUserId(), userRequest.getUserPw());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        Optional.ofNullable(authentication).orElseThrow(UserException.UserInfoNotFoundException::new);
        jwtUtil.createToken(userRequest, "Access");
        return responseService.getSuccessResult();
    }

    public CommonResult updateUser(UserRequest.UserInfo userRequest){
        int userIdx = Math.toIntExact(userRequest.getUserIdx());
        User user = userRepository.findById(userIdx).orElseThrow(() -> DOES_NOT_EXIST_EXCEPTION);

        if(!Objects.equals(user.getUserIdx(), jwtUtil.getUserIdx())) {
            throw ABNORMAL_ACCESS_EXCEPTION;
        }

        if(!userRequest.getUserPhone().isEmpty()){
            user.setUserPw(userRequest.getUserPw());
        }

        if(!userRequest.getUserPolicyYn().isEmpty()){
            user.setUserPolicyYn(userRequest.getUserPolicyYn());
        }

        if(!userRequest.getUserNickName().isEmpty()){
            user.setUserNickName(userRequest.getUserNickName());
        }

        userRepository.save(user);

        return responseService.getSuccessResult();
    }
}
