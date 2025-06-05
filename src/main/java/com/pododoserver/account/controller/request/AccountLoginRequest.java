package com.pododoserver.account.controller.request;

import com.pododoserver.account.dto.AccountMstDto;
import com.pododoserver.common.constant.ErrorMessage;
import com.pododoserver.common.exception.BaseException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountLoginRequest {

    private String accountLoginId;
    private String accountLoginPw;

    public void validate() {
        if (accountLoginId == null || accountLoginId.isBlank()
                || accountLoginPw == null   || accountLoginPw.isBlank()) {
            throw new BaseException(ErrorMessage.REQUIRED_EMPTY_PARAM);
        }
    }

    public AccountMstDto toDto() {
        return AccountMstDto.builder()
                .accountLoginId(this.accountLoginId)
                .accountLoginPw(this.accountLoginPw)
                .build();
    }
}
