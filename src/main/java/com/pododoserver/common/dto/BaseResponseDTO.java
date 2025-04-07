package com.pododoserver.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pododoserver.common.constant.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseDTO<T> {

    private String code;
    private String message;
    private T data;

    public void setResponse(Map<String, String> response){
        this.code = response.get("code");
        this.message = response.get("message");
    }

    public void setResponseErrorMessage(ErrorMessage errorMessage){
        this.code = errorMessage.getCode();
        this.message = errorMessage.getMessage();
    }
}
