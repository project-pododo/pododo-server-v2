package com.pododoserver.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    // 90000 ~ 99999 오류 메세지
    FAIL("90000", "실패"),
    FAIL_REGISTER("90001", "등록에 실패하였습니다."),
    SELECT_EMPTY("90002", "조회 결과가 존재하지 않습니다."),

    REQUIRED_EMPTY_PARAM("91000", "필수 파라미터가 존재하지 않습니다."),
    INVALID_REQUEST("91001", "유효하지 않은 요청입니다."),
    INVALID_REQUEST_PARAM("91002", "[{0}] 유효하지 않은 요청입니다."),
    INVALID_RANGE_DATE_PARAM("91003", "유효하지 않은 날짜 범위입니다."),
    INVALID_UPDATE("91004", "변경할 수 없는 상태입니다."),

    NOT_FOUND_DATA("92000", "회원정보가 존재 하지 않습니다."),
    NOT_CORRECT_PASSWORD("92001", "비밀번호가 일치하지 않습니다."),

    MISSING_REQUEST_HEADER("93000", "필수 헤더가 존재하지 않습니다."),
    WRONG_TOKEN("93001", "잘못된 토큰입니다."),
    UNAUTHORIZED_TOKEN("93002", "인증되지 않은 토큰입니다."),
    UNAUTHORIZED_TOKEN_RIGHT("93003", "권한이 없습니다: 토큰 ID와 요청 ID 불일치");
    ;

    private static final Map<String, ErrorMessage> map = Stream.of(values())
            .collect(Collectors.toMap(ErrorMessage::getCode, Function.identity()));

    public static ErrorMessage getBaseMsg(String code) {
        return map.get(code);
    }

    private final String code;
    private final String message;
}
