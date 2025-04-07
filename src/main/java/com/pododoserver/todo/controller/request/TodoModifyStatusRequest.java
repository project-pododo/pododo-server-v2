package com.pododoserver.todo.controller.request;

import com.pododoserver.common.constant.ErrorMessage;
import com.pododoserver.common.exception.BaseException;
import com.pododoserver.todo.constant.TodoStatus;
import com.pododoserver.todo.dto.TodoMstDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoModifyStatusRequest {

    private Long todoMstId;

    public void validate() {
        if (todoMstId == null ) {
            throw new BaseException(ErrorMessage.REQUIRED_EMPTY_PARAM);
        }
    }
}
