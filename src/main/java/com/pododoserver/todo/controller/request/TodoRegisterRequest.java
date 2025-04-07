package com.pododoserver.todo.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pododoserver.common.constant.ErrorMessage;
import com.pododoserver.common.exception.BaseException;
import com.pododoserver.todo.dto.TodoMstDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TodoRegisterRequest {

    private String todoName;
    private String todoDetail;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    public void validate() {
        if (todoName == null || todoName.trim().isEmpty() ||
                todoDetail == null || startDate == null || endDate == null) {
            throw new BaseException(ErrorMessage.REQUIRED_EMPTY_PARAM);
        }

        if (endDate.isBefore(startDate)) {
            throw new BaseException(ErrorMessage.INVALID_RANGE_DATE_PARAM);
        }
    }

    public TodoMstDto toDto() {
        return TodoMstDto.builder()
                .todoName(this.todoName)
                .todoDetail(this.todoDetail)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }
}
