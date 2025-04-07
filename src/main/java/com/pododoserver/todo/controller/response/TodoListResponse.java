package com.pododoserver.todo.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pododoserver.todo.constant.TodoStatus;
import com.pododoserver.todo.constant.UseYn;
import com.pododoserver.todo.entity.TodoMstET;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TodoListResponse {

    private Long todoMstId;
    private TodoStatus todoStatus;
    private String todoName;
    private String todoDetail;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    private UseYn useYn;

    public TodoListResponse(TodoMstET entity) {
        todoMstId = entity.getTodoMstId();
        todoStatus = entity.getTodoStatus();
        todoName = entity.getTodoName();
        todoDetail = entity.getTodoDetail();
        startDate = entity.getStartDate();
        endDate = entity.getEndDate();
        useYn = entity.getUseYn();
    }

    public static List<TodoListResponse> of(List<TodoMstET> entityList) {
        return entityList.stream()
                .map(TodoListResponse::new)
                .collect(Collectors.toList());
    }
}
