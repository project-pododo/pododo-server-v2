package com.pododoserver.todo.constant;

import com.pododoserver.common.constant.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UseYn implements BaseEnum {
    Y("Y", "사용"),
    N("N", "사용안함");

    private final String codeId;
    private final String codeNm;
}
