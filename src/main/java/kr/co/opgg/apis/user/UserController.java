package kr.co.opgg.apis.user;

import kr.co.opgg.apis.common.dto.CommonResult;
import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.apis.user.dto.UserResponse;
import kr.co.opgg.common.exception.UserException;
import kr.co.opgg.utils.validate.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<SingleResult<UserResponse>> insertUser(@Valid UserRequest userRequest, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        SingleResult<UserResponse> userResponse = userService.insertUser(userRequest);
        Optional.ofNullable(userResponse).orElseThrow(UserException.UserCreateException::new);

        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{userIdx}")
    public ResponseEntity<CommonResult> updateUser(@Valid UserRequest.UserInfo userRequest, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        CommonResult commonResult = userService.updateUser(userRequest);

        return ResponseEntity.ok(commonResult);
    }

    @GetMapping("/{userPhone}")
    public ResponseEntity<SingleResult<UserResponse>> findUserId(@Valid UserRequest.UserPrivateRequest userPrivateRequest, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        SingleResult<UserResponse> userResponse = Optional.ofNullable(userService.findUserId(userPrivateRequest)).orElseThrow(UserException.UserInfoNotFoundException::new);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/login")
    public ResponseEntity<CommonResult> loginUser(@Valid UserRequest userRequest, BindingResult bindingResult){
        ValidateUtil.validateBindingResult(bindingResult);

        return ResponseEntity.ok(userService.loginUser(userRequest));
    }
}
