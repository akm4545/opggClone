package kr.co.opgg.apis.common.dto;

import lombok.Builder;
import lombok.Data;

public class CommonRequest {
    @Builder
    @Data
    public static class File{
        String fileName;
    }
}
