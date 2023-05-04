package kr.co.opgg.apis.declaration.dto;

import lombok.Data;

public class DeclarationRequest {

    @Data
    public static class DeclarationBoard{
        Integer boardIdx;

        String declarationContent;
    }
}
