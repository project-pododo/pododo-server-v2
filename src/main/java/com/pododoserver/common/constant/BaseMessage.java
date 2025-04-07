package com.pododoserver.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum BaseMessage {

    // 10000 ~ 19999 기본 메세지
    SUCCESS_OK("10000", "성공"),
    SUCCESS_REGISTER("10001", "정상적으로 등록되었습니다."),
    SUCCESS_MODIFY("10002", "정상적으로 수정되었습니다."),
    SUCCESS_DELETE("10003", "정상적으로 삭제되었습니다."),
    SUCCESS_RESTORE("10004", "정상적으로 복구되었습니다."),
    ;

    private static final Map<String, BaseMessage> map = Stream.of(values())
            .collect(Collectors.toMap(BaseMessage::getCode, Function.identity()));

    public static BaseMessage getBaseMessage(String code) {
        return map.get(code);
    }

    private final String code;
    private final String message;
}
