package com.pododoserver.todo.repository;

import com.pododoserver.todo.constant.TodoStatus;
import com.pododoserver.todo.constant.UseYn;
import com.pododoserver.todo.entity.TodoMstET;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoMstRepository extends JpaRepository<TodoMstET, Long> {

    List<TodoMstET> findByTodoStatusAndUseYn(TodoStatus todoStatus, UseYn useYn);
    List<TodoMstET> findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndTodoStatusAndUseYn(LocalDateTime startDate, LocalDateTime endDate, TodoStatus todoStatus, UseYn useYn);
    List<TodoMstET> findByUseYn(UseYn useYn);
}
