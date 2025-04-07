package com.pododoserver.todo.constant;

import com.pododoserver.common.constant.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestCd implements BaseEnum {

    Y("Y", "활성"),
    N("N", "비활성"),
    ;

    private final String codeId;
    private final String codeNm;
}