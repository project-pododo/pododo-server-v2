package com.pododoserver.todo.dto;

import com.pododoserver.todo.constant.TodoStatus;
import com.pododoserver.todo.constant.UseYn;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TodoMstDto {

    private final Long todoMstId;
    private final TodoStatus todoStatus;
    private final String todoName;
    private final String todoDetail;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final UseYn useYn;
}
