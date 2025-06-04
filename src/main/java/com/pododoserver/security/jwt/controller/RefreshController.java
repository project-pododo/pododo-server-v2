package com.pododoserver.security.jwt.controller;

import com.pododoserver.common.BaseController;
import com.pododoserver.common.constant.BaseMessage;
import com.pododoserver.common.dto.BaseResponseDTO;
import com.pododoserver.security.jwt.controller.response.JwtResponse;
import com.pododoserver.security.jwt.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/v2/token")
@RequiredArgsConstructor
@Slf4j
public class RefreshController extends BaseController {

    private final RefreshTokenService refreshTokenService;

    @Operation(summary = "Refresh Token 발급 API")
    @PostMapping("/refresh")
    public ResponseEntity<BaseResponseDTO<JwtResponse>> refreshToken(WebRequest webRequest,
                             @RequestHeader("Refresh-Token") String refreshToken) {

        return getResOK(webRequest, BaseMessage.SUCCESS_OK, refreshTokenService.reIssue(refreshToken));
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<BaseResponseDTO<Void>> logout(WebRequest webRequest,
                                                        @RequestHeader("Refresh-Token") String refreshToken) {

        refreshTokenService.deleteRefreshToken(refreshToken);

        return getResOK(webRequest, BaseMessage.SUCCESS_LOGOUT);
    }
}
