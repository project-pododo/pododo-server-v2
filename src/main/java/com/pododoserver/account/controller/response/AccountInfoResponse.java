package com.pododoserver.account.controller.response;

import com.pododoserver.account.constant.Role;
import com.pododoserver.account.entity.AccountET;
import lombok.Getter;

@Getter
public class AccountInfoResponse {

    private final Long accountMstId;
    private final String accountLoginId;
    private final String accountLoginPassword;
    private final String accountEmail;
    private final Role role;
    private final String accountName;

    public AccountInfoResponse(AccountET e) {
        this.accountMstId = e.getAccountMstId();
        this.accountLoginId = e.getAccountLoginId();
        this.accountLoginPassword = e.getAccountLoginPw();
        this.accountEmail = e.getAccountEmail();
        this.role = e.getRole();
        this.accountName = e.getAccountName();
    }

    public static AccountInfoResponse of(AccountET entity) {
        return new AccountInfoResponse(entity);
    }
}
