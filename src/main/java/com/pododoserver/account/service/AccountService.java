package com.pododoserver.account.service;

import com.pododoserver.account.entity.AccountET;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountImplService accountImplService;

    public void getAccountInfoById() {
        AccountET accountET = accountImplService.findById(1L);
        System.out.println("");

    }
}
