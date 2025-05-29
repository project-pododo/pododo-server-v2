package com.pododoserver.security.jwt.service;

import com.pododoserver.security.jwt.entity.RefreshTokenET;
import com.pododoserver.security.jwt.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.pododoserver.common.constant.Constants.REFRESH_TOKEN_EXPIRATION_MS;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(Long accountMstId, String refreshToken) {
        RefreshTokenET refreshTokenET = RefreshTokenET.builder()
                .accountMstId(accountMstId)
                .token(refreshToken)
                .issuedAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_EXPIRATION_MS/1000L))
                .build();
        refreshTokenRepository.save(refreshTokenET);
    }
}
