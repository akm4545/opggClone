package kr.co.opgg.apis.user.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
public class UserRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String userPw;

    @NotBlank
    private String userNickName;

    @NotBlank
    private String userPolicyYN;

    @NotBlank
    private String authorityIdx;

    @Builder
    public UserRequest(String userId, String userPw, String userNickName, String userPolicyYN, String authorityIdx){
        this.userId = userId;
        this.userPw = userPw;
        this.userNickName = userNickName;
        this.userPolicyYN = userPolicyYN;
        this.authorityIdx = authorityIdx;
    }

}
