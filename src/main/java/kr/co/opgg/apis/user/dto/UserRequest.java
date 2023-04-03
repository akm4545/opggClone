package kr.co.opgg.apis.user.dto;


import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
public class UserRequest {

    @NotBlank(message = "아이디가 공백입니다.")
    private String userId;

    @NotBlank(message = "비밀번호가 공백입니다.")
    private String userPw;

    @NotBlank(message = "닉네임이 공백입니다.")
    private String userNickName;

    @NotBlank(message = "유저 정책 동의여부가 없습니다.")
    private String userPolicyYN;

    @NotBlank(message = "유저의 권한이 없습니다.")
    private String authorityIdx;
}
