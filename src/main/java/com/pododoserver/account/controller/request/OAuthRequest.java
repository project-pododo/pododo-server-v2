package com.pododoserver.account.controller.request;

import com.pododoserver.account.dto.GoogleOAuthDto;
import lombok.Getter;

@Getter
public class OAuthRequest {
    private String code;
    private String state;


    public GoogleOAuthDto toDto() {
        return GoogleOAuthDto.builder()
                .code(this.code)
                .state(this.state)
                .build();
    }
}
