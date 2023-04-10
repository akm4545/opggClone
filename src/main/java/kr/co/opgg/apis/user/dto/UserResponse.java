package kr.co.opgg.apis.user.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponse {

    private Long userIdx;

    private String userId;

    private String userPw;

    @Builder
    public UserResponse(Long userIdx, String userId, String userPw){
        this.userIdx = userIdx;
        this.userId = userId;
        this.userPw = userPw;
    }

}
