package com.pododoserver.common;

import com.pododoserver.common.constant.BaseMessage;
import com.pododoserver.common.dto.BaseResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class BaseController {

    // todo public <T> ResponseEntity 정의 -> 헤더 정의

    public <T> ResponseEntity<BaseResponseDTO<T>> getResOK(WebRequest webReq, BaseMessage baseMsg, T data) {
        BaseResponseDTO<T> response = new BaseResponseDTO<>(baseMsg.getCode(), baseMsg.getMessage(), data);
        return ResponseEntity.ok(response);
    }

    public <T> ResponseEntity<BaseResponseDTO<T>> getResOK(WebRequest webReq, BaseMessage baseMsg) {
        BaseResponseDTO<T> response = new BaseResponseDTO<>(baseMsg.getCode(), baseMsg.getMessage(), null);
        return ResponseEntity.ok(response);
    }

    public <T> ResponseEntity<BaseResponseDTO<T>> getResOK(WebRequest webReq, BaseResponseDTO<T> baseResponseDTO) {
        BaseResponseDTO<T> response = new BaseResponseDTO<>(baseResponseDTO.getCode(), baseResponseDTO.getMessage(), baseResponseDTO.getData());
        return ResponseEntity.ok(response);
    }
}
