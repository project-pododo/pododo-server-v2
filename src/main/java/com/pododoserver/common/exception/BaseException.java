package com.pododoserver.common.exception;

import com.pododoserver.common.constant.ErrorMessage;
import lombok.Getter;

import java.io.Serial;

import static com.pododoserver.common.util.ResMessageUtil.mapping;

@Getter
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String codeId;
    private Object[] params;

    public BaseException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.codeId = errorMessage.getCode();
    }

    public BaseException(ErrorMessage errorMessage, Object... params) {
        super(mapping(errorMessage.getMessage(), params));
        this.codeId = errorMessage.getCode();
        this.params = params;
    }
}
