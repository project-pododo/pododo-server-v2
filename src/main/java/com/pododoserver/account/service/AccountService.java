package com.pododoserver.account.service;

import com.pododoserver.account.constant.Role;
import com.pododoserver.account.controller.response.AccountInfoResponse;
import com.pododoserver.account.dto.AccountMstDto;
import com.pododoserver.account.entity.AccountET;
import com.pododoserver.common.exception.BaseException;
import com.pododoserver.common.constant.ErrorMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountImplService accountImplService;
    private final PasswordEncoder passwordEncoder;

    public void registerAccount(AccountMstDto dto) {

        String encodedPw = passwordEncoder.encode(dto.getAccountLoginPw());
        AccountET entity = AccountET.builder()
                .accountLoginId(dto.getAccountLoginId())
                .accountLoginPw(encodedPw)
                .accountEmail(dto.getAccountEmail())
                .accountName(dto.getAccountName())
                .role(Role.USER)
                .build();
        accountImplService.save(entity);
    }

    public void updateAccount(AccountMstDto dto) {
        AccountET entity = accountImplService.findById(dto.getAccountMstId());
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
        AccountET accountET = accountImplService.findById(accountMstId);
        if (accountET == null) {
            throw new BaseException(ErrorMessage.NOT_FOUND_DATA);
        }
        return accountET;
    }
}
