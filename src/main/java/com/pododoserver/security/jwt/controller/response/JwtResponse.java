package com.pododoserver.security.jwt.controller.response;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class JwtResponse {
    private final String accessToken;
    private final String refreshToken;
    private final String tokenType;
}
