package com.pododoserver.account.controller.response;

import com.pododoserver.account.constant.Role;
import com.pododoserver.account.entity.AccountET;
import lombok.Getter;

@Getter
public class AccountInfoResponse {

    private Long   accountMstId;
    private String accountLoginId;
    private String accountLoginPassword;
    private String accountEmail;
    private Role role;
    private String accountName;

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
