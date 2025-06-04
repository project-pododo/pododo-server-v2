package com.pododoserver.security.jwt.service;

import com.pododoserver.account.constant.Role;
import com.pododoserver.account.entity.AccountET;
import com.pododoserver.common.constant.ErrorMessage;
import com.pododoserver.common.exception.BaseException;
import com.pododoserver.security.jwt.JwtTokenProvider;
import com.pododoserver.security.jwt.controller.response.JwtResponse;
import com.pododoserver.security.jwt.entity.RefreshTokenET;
import com.pododoserver.security.jwt.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

import static com.pododoserver.common.constant.Constants.REFRESH_TOKEN_EXPIRATION_MS;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void saveRefreshToken(Long accountMstId, String refreshToken) {
        RefreshTokenET refreshTokenET = RefreshTokenET.builder()
                .accountMstId(accountMstId)
                .token(refreshToken)
                .issuedAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_EXPIRATION_MS/1000L))
                .build();
        refreshTokenRepository.save(refreshTokenET);
    }

    @Transactional
    public JwtResponse reIssue(String refreshToken) {

        if (!StringUtils.hasText(refreshToken)) {
            throw new BaseException(ErrorMessage.WRONG_TOKEN);
        }

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new BaseException(ErrorMessage.UNAUTHORIZED_TOKEN);
        }

        // accessToken, refreshToken 재발급
        Long accountMstId = jwtTokenProvider.getAccountMstId(refreshToken);
        String accountLoingId = jwtTokenProvider.getAccountLoginId(refreshToken);
        String role = jwtTokenProvider.getAccountRole(refreshToken);

        AccountET accountET = AccountET.builder()
                .accountMstId(accountMstId)
                .accountLoginId(accountLoingId)
                .role(Role.valueOf(role))
                .build();

        String newAccessToken = jwtTokenProvider.generateToken(accountET);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(accountET);


        //refreshToken db 삭제
        refreshTokenRepository.deleteByToken(refreshToken);

        //refreshToken db 저장
        saveRefreshToken(accountMstId, newRefreshToken);


        return JwtResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .tokenType("Bearer")
                .build();
    }
}
