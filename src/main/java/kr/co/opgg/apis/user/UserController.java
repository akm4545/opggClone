package kr.co.opgg.apis.user;

import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.apis.user.dto.UserResponse;
import kr.co.opgg.apis.user.service.UserService;
import kr.co.opgg.common.exception.UserException;
import kr.co.opgg.datasource.user.User;
import kr.co.opgg.utils.validate.ValidateUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserResponse> insertUser(@Valid UserRequest userRequest, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        User user = userService.insertUser(userRequest);
        Optional.ofNullable(user).orElseThrow(UserException.UserCreateException::new);
        UserResponse userResponse = UserResponse.builder()
                .userIdx(user.getUserIdx())
                .build();


        return ResponseEntity.ok(userResponse);
    }
}
