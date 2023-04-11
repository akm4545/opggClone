package userService;

import kr.co.opgg.Main;
import kr.co.opgg.apis.common.dto.SingleResult;
import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.apis.user.UserService;
import kr.co.opgg.apis.user.dto.UserResponse;
import kr.co.opgg.common.exception.JwtTokenException;
import kr.co.opgg.common.jwttoken.JwtUtil;
import kr.co.opgg.common.jwttoken.TokenDto;
import kr.co.opgg.datasource.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Main.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Test
    @DisplayName("유저 회원가입 테스트")
    public void 유저회원가입(){
        //given
        UserRequest user = UserRequest.builder()
                .userId("aabb")
                .userPw("1234")
                .userNickName("asdgasg")
                .userPolicyYN("Y")
                .authorityIdx("qwe")
                .build();
        //when
        SingleResult<UserResponse> newUser = userService.insertUser(user);

        //then
        assertThat(user.getUserId()).isEqualTo(newUser.getData().getUserId());
    }


    @Test
    @DisplayName("토큰 발급 테스트")
    public void 토큰발급(){
        //given
        String userId = "test";
        //when
        TokenDto tokenDto = jwtUtil.createAllToken(userId);

        //then
        Optional.ofNullable(tokenDto.getAccessToken()).orElseThrow(JwtTokenException.NotCreateToken::new);
        Optional.ofNullable(tokenDto.getRefreshToken()).orElseThrow(JwtTokenException.NotCreateToken::new);
        assertThat(tokenDto.getAccessToken()).isNotBlank();
        assertThat(tokenDto.getRefreshToken()).isNotBlank();
    }

}
