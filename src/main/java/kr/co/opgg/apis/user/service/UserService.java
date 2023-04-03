package kr.co.opgg.apis.user.service;

import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.datasource.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface UserService {
    User insertUser(UserRequest userRequest);
}
