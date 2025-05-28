package com.pododoserver.account.controller.request;

import com.pododoserver.account.dto.AccountMstDto;
import com.pododoserver.common.constant.ErrorMessage;
import com.pododoserver.common.exception.BaseException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountRegisterRequest {

    private String accountLoginId;
    private String accountLoginPw;
    private String accountEmail;
    private String accountName;

    public void validate() {
        if (accountLoginId == null || accountLoginPw == null
                || accountName == null || accountEmail == null) {
            throw new BaseException(ErrorMessage.REQUIRED_EMPTY_PARAM);
        }
    }

    public AccountMstDto toDto() {
        return AccountMstDto.builder()
                .accountLoginId(accountLoginId)
                .accountLoginPw(accountLoginPw)
                .accountEmail(accountEmail)
                .accountName(accountName)
                .build();
    }
}
