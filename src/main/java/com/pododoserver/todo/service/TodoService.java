package com.pododoserver.todo.service;

import com.pododoserver.account.entity.AccountET;
import com.pododoserver.common.constant.ErrorMessage;
import com.pododoserver.common.exception.BaseException;
import com.pododoserver.todo.constant.TodoStatus;
import com.pododoserver.todo.constant.UseYn;
import com.pododoserver.todo.dto.TodoMstDto;
import com.pododoserver.todo.dto.TodoSearchDto;
import com.pododoserver.todo.entity.TodoMstET;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoServiceImpl todoServiceImpl;

    public void saveTodo(TodoMstDto todoMstDto) {
        todoServiceImpl.save(TodoMstET.builder()
                .accountET(AccountET.builder()
                        .accountMstId(1L)
                        .build())
                .todoStatus(TodoStatus.WAIT)
                .todoName(todoMstDto.getTodoName())
                .todoDetail(todoMstDto.getTodoDetail())
                .startDate(todoMstDto.getStartDate())
                .endDate(todoMstDto.getEndDate())
                .useYn(UseYn.Y)
                .build());
    }

    public List<TodoMstET> findWaitTodoList() {
        List<TodoMstET> todoMstETList = todoServiceImpl.findByTodoStatus(TodoStatus.WAIT);
        return todoMstETList;
    }

    public List<TodoMstET> findCompletedTodoList(TodoSearchDto searchDto) {
        List<TodoMstET> todoMstETList;

        if (searchDto.isAllSearch()) {
            todoMstETList = todoServiceImpl.findByTodoStatus(TodoStatus.DONE);
        } else {
            todoMstETList = todoServiceImpl.findByDate(searchDto.getStart(), searchDto.getEnd(), TodoStatus.DONE);
        }
        return todoMstETList;
    }

    public List<TodoMstET> finRubbishTodoList() {
        List<TodoMstET> todoMstETList = todoServiceImpl.findByUseYn(UseYn.N);
        return todoMstETList;
    }

    public void modifyTodoInfo(TodoMstDto todoMstDto) {
        TodoMstET todo = todoServiceImpl.findById(todoMstDto.getTodoMstId())
                .orElseThrow(() -> new BaseException(ErrorMessage.SELECT_EMPTY));

        todo.updateAllInfo(todoMstDto);
    }

    public void modifyTodoStatus(Long todoMstId) {
        TodoMstET todo = todoServiceImpl.findById(todoMstId)
                .orElseThrow(() -> new BaseException(ErrorMessage.SELECT_EMPTY));

        todo.updateStatus(todo.getTodoStatus());
    }

    public void deleteTodo(Long todoMstId) {
        TodoMstET todo = todoServiceImpl.findById(todoMstId)
                .orElseThrow(() -> new BaseException(ErrorMessage.SELECT_EMPTY));

        if (todo.getUseYn().equals(UseYn.N)) throw new BaseException(ErrorMessage.INVALID_UPDATE);

        todo.delete();
    }

    public void restoreTodo(Long todoMstId) {
        TodoMstET todo = todoServiceImpl.findById(todoMstId)
                .orElseThrow(() -> new BaseException(ErrorMessage.SELECT_EMPTY));

        if (todo.getUseYn().equals(UseYn.Y)) throw new BaseException(ErrorMessage.INVALID_UPDATE);

        todo.restore();
    }
}
