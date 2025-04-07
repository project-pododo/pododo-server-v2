package com.pododoserver.todo.constant;

import com.pododoserver.common.constant.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TodoStatus implements BaseEnum {

    WAIT("WAIT", "대기"),
    DONE("DONE", "완료"),
    ;

    private final String codeId;
    private final String codeNm;
}
