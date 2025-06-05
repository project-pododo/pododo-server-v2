package com.pododoserver.security.jwt.controller.response;

import com.pododoserver.account.controller.response.AccountInfoResponse;
import com.pododoserver.account.entity.AccountET;
import com.pododoserver.security.jwt.entity.RefreshTokenET;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class JwtResponse {
    private final String accessToken;
    private final String refreshToken;
    private final String tokenType;
}
