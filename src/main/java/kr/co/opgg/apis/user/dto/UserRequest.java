package kr.co.opgg.apis.user.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@ToString
public class UserRequest {

    private int userIdx;
    @NotBlank
    private String userId;

    @NotBlank
    private String userPw;

    private String userPhone;

    private String userNickName;

    private String userPolicyYN;

    private int authorityIdx;


    @Builder
    public UserRequest(int userIdx, String userId, String userPw, String userPhone, String userNickName, String userPolicyYN, int authorityIdx){
        this.userIdx = userIdx;
        this.userId = userId;
        this.userPw = userPw;
        this.userNickName = userNickName;
        this.userPolicyYN = userPolicyYN;
        this.authorityIdx = authorityIdx;
        this.userPhone = userPhone;
    }

    @Getter
    public static class UserPrivateRequest {
        @NotBlank
        private String userPhone;
    }

    @Data
    public static class UserInfo {
        @NotBlank
        private int userIdx;

        @NotBlank
        private String userPw;

        private String userPhone;

        private String userNickName;

        private String userPolicyYn;
    }

}
