package com.pododoserver.common.util;

import com.pododoserver.common.constant.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ResMessageUtil {

    private static final String PARAM_START = "{";
    private static final String PARAM_END = "}";

    /**
     * 응답 메시지 선택(파라미터 포함)
     */
    public static Map<String, String> getMessage(ErrorMessage errorMessage, Object[] params) {
        Map<String, String> resMsgMap = new HashMap<>();
        resMsgMap.put("code", errorMessage.getCode());
        resMsgMap.put("message", mapping(errorMessage.getMessage(), params));
        return resMsgMap;
    }

    public static String mapping(String message, Object[] params) {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                message = message.replace(PARAM_START + i + PARAM_END, String.valueOf(params[i]));
            }
        }
        return message;
    }
}
