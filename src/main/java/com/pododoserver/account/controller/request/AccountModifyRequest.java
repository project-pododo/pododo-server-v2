package com.pododoserver.account.controller.request;

import com.pododoserver.account.dto.AccountMstDto;
import com.pododoserver.common.constant.ErrorMessage;
import com.pododoserver.common.exception.BaseException;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountModifyRequest {

    private Long accountMstId;
    private String accountLoginPw;
    private String accountName;


    public void validate() {
        if (accountMstId == null) {
            throw new BaseException(ErrorMessage.REQUIRED_EMPTY_PARAM);
        }
    }

    public AccountMstDto toDto() {
        return AccountMstDto.builder()
                .accountMstId(accountMstId)
                .accountLoginPw(accountLoginPw)
                .accountName(accountName)
                .build();
    }
}
