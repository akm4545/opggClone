package kr.co.opgg.datasource.user;

import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.apis.user.dto.UserResponse;

public interface UserRepositoryCustom {

    UserResponse findByUserId(String userId);

    UserResponse findUserIdByPhone(UserRequest.UserPrivateRequest userRequest);

}
