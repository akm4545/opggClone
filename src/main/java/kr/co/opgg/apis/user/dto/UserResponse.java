package kr.co.opgg.apis.user.dto;


import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserResponse {

    private Long userIdx;

    @Builder
    public UserResponse(Long userIdx){
        this.userIdx = userIdx;
    }

}
