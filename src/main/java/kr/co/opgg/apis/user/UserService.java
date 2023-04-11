package kr.co.opgg.apis.user;

import kr.co.opgg.apis.common.ResponseService;
import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.apis.user.dto.UserResponse;
import kr.co.opgg.common.exception.UserException;
import kr.co.opgg.datasource.user.User;
import kr.co.opgg.datasource.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;

    private final ResponseService responseService;

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

    public User loginUser(UserRequest userRequest) {
        //todo jwtToken 생성
        User user = userRepository.loginUser(userRequest);

        return null;
    }
}
