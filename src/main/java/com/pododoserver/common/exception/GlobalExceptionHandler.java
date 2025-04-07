package com.pododoserver.common.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.pododoserver.common.constant.ErrorMessage;
import com.pododoserver.common.dto.BaseResponseDTO;
import com.pododoserver.common.util.ResMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponseDTO<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        BaseResponseDTO errorRes = new BaseResponseDTO<>();
        errorRes.setResponseErrorMessage(ErrorMessage.INVALID_REQUEST);
        return getResponse(e, errorRes);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponseDTO<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BaseResponseDTO errorRes = new BaseResponseDTO<>();
        errorRes.setResponseErrorMessage(ErrorMessage.INVALID_REQUEST);
        return getResponse(e, errorRes);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponseDTO<?>> handleBaseException(BaseException e) {
        BaseResponseDTO errorRes = new BaseResponseDTO<>();
        errorRes.setResponseErrorMessage(ErrorMessage.getBaseMsg(e.getCodeId()));
        return getResponse(e, errorRes);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<BaseResponseDTO<?>> handleJsonException(Exception e, WebRequest webReq) {
        BaseResponseDTO errorRes = new BaseResponseDTO<>();

        Throwable cause = e.getCause();
        if (cause instanceof JsonMappingException jsonMappingException) {
            jsonMappingException.getPath().stream()
                    .reduce((first, second) -> second)
                    .map(JsonMappingException.Reference::getFieldName)
                    .ifPresent(fieldName -> errorRes.setResponse(ResMessageUtil.getMessage(ErrorMessage.INVALID_REQUEST_PARAM, fieldName.lines().toArray())));
        } else {
            errorRes.setResponseErrorMessage(ErrorMessage.INVALID_REQUEST);
        }

        return getResponse(e, errorRes);
    }

    private ResponseEntity<BaseResponseDTO<Object>> getResponse(Exception e, BaseResponseDTO<Object> response) {

        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
