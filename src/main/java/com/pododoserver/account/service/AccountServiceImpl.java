package com.pododoserver.account.service;

import com.pododoserver.account.entity.AccountET;
import com.pododoserver.account.repository.AccountMstRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl {

    private final AccountMstRepository accountMstRepository;

    // todo orElseThrow custom
    public AccountET findById(Long accountMstId){
        return accountMstRepository.findById(accountMstId).orElse(null);
    }
    public AccountET save(AccountET entity) {
        return accountMstRepository.save(entity);
    }
}
