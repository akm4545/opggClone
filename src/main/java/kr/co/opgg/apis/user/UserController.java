package kr.co.opgg.apis.user;

import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.apis.user.dto.UserResponse;
import kr.co.opgg.apis.user.service.UserService;
import kr.co.opgg.common.exception.UserException;
import kr.co.opgg.datasource.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity insertUser(@Valid UserRequest userRequest){
        User user = userService.insertUser(userRequest);
        Optional.ofNullable(user).orElseThrow(UserException.UserCreateException::new);
        UserResponse userResponse = UserResponse.builder()
                .userIdx(user.getUserIdx())
                .build();
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
