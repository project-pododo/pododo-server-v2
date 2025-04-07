package com.pododoserver.todo.service;

import com.pododoserver.todo.constant.TodoStatus;
import com.pododoserver.todo.constant.UseYn;
import com.pododoserver.todo.entity.TodoMstET;
import com.pododoserver.todo.repository.TodoMstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl {

    private final TodoMstRepository todoMstRepository;

    public TodoMstET save(TodoMstET entity) {
        return todoMstRepository.save(entity);
    }

    public List<TodoMstET> findByTodoStatus(TodoStatus todoStatus) {
        return todoMstRepository.findByTodoStatusAndUseYn(todoStatus, UseYn.Y);
    }

    public List<TodoMstET> findByDate(LocalDateTime startDate, LocalDateTime endDate, TodoStatus todoStatus) {
        return todoMstRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndTodoStatusAndUseYn(startDate, endDate, todoStatus, UseYn.Y);
    }

    public List<TodoMstET> findByUseYn(UseYn useYn) {
        return todoMstRepository.findByUseYn(useYn);
    }

    public Optional<TodoMstET> findById(Long id) {
        return todoMstRepository.findById(id);
    }
}
