package kr.co.opgg.apis.user;

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

    public User insertUser(UserRequest userRequest) {
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

        return userRepository.save(joinUser);
    }

    public UserResponse findUserId(@Valid UserRequest.UserPrivateRequest userRequest) {
        UserResponse userResponse = userRepository.findUserIdByPhone(userRequest);

        return userResponse;
    }
}
