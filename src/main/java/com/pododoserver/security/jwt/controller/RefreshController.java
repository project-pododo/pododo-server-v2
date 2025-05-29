package com.pododoserver.security.jwt.controller;

import com.pododoserver.common.BaseController;
import com.pododoserver.common.constant.BaseMessage;
import com.pododoserver.common.dto.BaseResponseDTO;
import com.pododoserver.security.jwt.JwtTokenProvider;
import com.pododoserver.security.jwt.controller.response.JwtResponse;
import com.pododoserver.security.jwt.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/v2/token")
@RequiredArgsConstructor
public class RefreshController extends BaseController {

    private final RefreshTokenService refreshTokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "재발급")
    @PostMapping("/refresh")
    public ResponseEntity<BaseResponseDTO<JwtResponse>> refresh(WebRequest webRequest,
                            @CookieValue(name = "refreshToken", required = false) String refreshToken) {

//        JwtResponse body = JwtResponse.of(newAccessToken, jwtTokenProvider);
        return getResOK(webRequest, BaseMessage.SUCCESS_OK, null);
    }

}
