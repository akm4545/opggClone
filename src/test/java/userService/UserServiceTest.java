package userService;

import kr.co.opgg.Main;
import kr.co.opgg.apis.user.dto.UserRequest;
import kr.co.opgg.apis.user.service.UserService;
import kr.co.opgg.datasource.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Main.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

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
        User newUser = userService.insertUser(user);

        //then
        assertThat(user.getUserId()).isEqualTo(newUser.getUserId());
    }
}
