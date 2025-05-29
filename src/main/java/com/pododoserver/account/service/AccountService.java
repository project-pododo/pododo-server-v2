package com.pododoserver.account.service;

import com.pododoserver.account.constant.Role;
import com.pododoserver.account.dto.AccountMstDto;
import com.pododoserver.account.entity.AccountET;
import com.pododoserver.common.exception.BaseException;
import com.pododoserver.common.constant.ErrorMessage;
import com.pododoserver.security.jwt.JwtTokenProvider;
import com.pododoserver.security.jwt.controller.response.JwtResponse;
import com.pododoserver.security.jwt.service.RefreshTokenService;
import com.pododoserver.security.user.CustomUserDetails;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountServiceImpl accountServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtResponse login(AccountMstDto accountMstDto) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(accountMstDto.getAccountLoginId(), accountMstDto.getAccountLoginPw());

        Authentication auth = authManager.authenticate(authToken);
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        Long accountMstId = userDetails.getAccountMstId();

        String accessToken  = jwtTokenProvider.generateToken(auth);
        String refreshToken = jwtTokenProvider.generateRefreshToken(auth);
        refreshTokenService.saveRefreshToken(accountMstId, refreshToken);

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .build();
    }

    public void registerAccount(AccountMstDto dto) {

        String encodedPw = passwordEncoder.encode(dto.getAccountLoginPw());
        AccountET entity = AccountET.builder()
                .accountLoginId(dto.getAccountLoginId())
                .accountLoginPw(encodedPw)
                .accountEmail(dto.getAccountEmail())
                .accountName(dto.getAccountName())
                .role(Role.USER)
                .build();
        accountServiceImpl.save(entity);
    }

    public void updateAccount(AccountMstDto dto) {
        AccountET entity = accountServiceImpl.findById(dto.getAccountMstId());
        if (entity == null) {
            throw new BaseException(ErrorMessage.NOT_FOUND_DATA);
        }
        if (dto.getAccountLoginPw() != null && !dto.getAccountLoginPw().isBlank()) {
            String encodedPw = passwordEncoder.encode(dto.getAccountLoginPw());
            entity.updatePw(encodedPw);
        }
        if (dto.getAccountName() != null) {
            entity.updateName(dto.getAccountName());
        }
    }

    @Transactional
    public AccountET getAccountInfo(Long accountMstId) {
        AccountET accountET = accountServiceImpl.findById(accountMstId);
        if (accountET == null) {
            throw new BaseException(ErrorMessage.NOT_FOUND_DATA);
        }
        return accountET;
    }
}
