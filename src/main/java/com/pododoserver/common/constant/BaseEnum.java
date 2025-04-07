package com.pododoserver.common.constant;

import org.apache.commons.lang3.ObjectUtils;

public interface BaseEnum {

    String getCodeId();

    static <T extends Enum<T> & BaseEnum> T getByCodeId(Class<T> enumType, String codeId) {
        if (ObjectUtils.isNotEmpty(codeId)){
            for (T data : enumType.getEnumConstants()) {
                if (data.getCodeId().equals(codeId)) {
                    return data;
                }
            }
        }
        return null;
    }
}
