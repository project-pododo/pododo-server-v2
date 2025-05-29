package com.pododoserver.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
public class AccountMstDto {
    private final Long   accountMstId;
    private final String accountLoginId;
    private final String accountLoginPw;
    private final String accountEmail;
    private final String accountName;
}
