package kr.co.opgg.apis.user.service;

import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.common.exception.UserException;
import kr.co.opgg.datasource.user.User;
import kr.co.opgg.datasource.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User insertUser(UserRequest userRequest) {
        User existUser = userRepository.findByUserId(userRequest.getUserId());
        if(!ObjectUtils.isEmpty(existUser)){
            throw new UserException.UserExistException();
        }

        User joinUser = User.builder()
                .userId(userRequest.getUserId())
                .userPw(userRequest.getUserPw())
                .userNickName(userRequest.getUserNickName())
                .userPolicyYn(userRequest.getUserPolicyYN())
                .build();

        return userRepository.save(joinUser);
    }
}
