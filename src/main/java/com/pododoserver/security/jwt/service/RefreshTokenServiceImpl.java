package com.pododoserver.security.jwt.service;

import com.pododoserver.security.jwt.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl {

    private final RefreshTokenRepository refreshTokenRepository;

}
