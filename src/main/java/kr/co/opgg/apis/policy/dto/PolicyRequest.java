package kr.co.opgg.apis.policy.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

public class PolicyRequest {

    @Data
    public static class PolicyType{
        @NotNull
        String policyType;
    }
}
