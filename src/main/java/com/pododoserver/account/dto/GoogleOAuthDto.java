package com.pododoserver.account.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GoogleOAuthDto {

    private String code;
    private String state;
    public GoogleOAuthDto(String code, String state) {
    }
}
