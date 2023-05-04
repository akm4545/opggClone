package kr.co.opgg.apis.declaration.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

public class DeclarationRequest {

    @Data
    public static class DeclarationBoard{
        @NotNull
        Integer boardIdx;

        @NotNull
        String declarationContent;
    }

    @Data
    public static class DeclarationComment{
        @NotNull
        Integer commentIdx;

        @NotNull
        String declarationContent;
    }
}
