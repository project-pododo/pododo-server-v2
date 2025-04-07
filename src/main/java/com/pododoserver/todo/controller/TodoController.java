package com.pododoserver.todo.controller;

import com.pododoserver.common.dto.BaseResponseDTO;
import com.pododoserver.todo.controller.request.TodoModifyInfoRequest;
import com.pododoserver.todo.controller.request.TodoModifyStatusRequest;
import com.pododoserver.todo.controller.response.TodoListResponse;
import com.pododoserver.common.constant.BaseMessage;
import com.pododoserver.todo.controller.request.TodoRegisterRequest;
import com.pododoserver.common.BaseController;
import com.pododoserver.todo.dto.TestDTO;
import com.pododoserver.todo.dto.TodoSearchDto;
import com.pododoserver.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/todo")
public class TodoController extends BaseController {

    private final TodoService todoService;

    @Operation(summary = "할일 조회 - 대기")
    @GetMapping
    public ResponseEntity<BaseResponseDTO<List<TodoListResponse>>> findWaitTodoList(WebRequest webReq,
                                                                                    HttpSession session) {
        return getResOK(webReq,
                BaseMessage.SUCCESS_OK, TodoListResponse.of(todoService.findWaitTodoList()));
    }

    @Operation(summary = "할일 조회 - 완료")
    @GetMapping("/completed")
    public ResponseEntity<BaseResponseDTO<List<TodoListResponse>>> findCompletedTodoList(WebRequest webReq,
                                          HttpSession session,
                                          @RequestParam(required = false, name="startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                          @RequestParam(required = false, name="endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        return getResOK(webReq,
                BaseMessage.SUCCESS_OK,
                TodoListResponse.of(todoService.findCompletedTodoList(TodoSearchDto.builder()
                        .startDate(startDate)
                        .endDate(endDate)
                        .build())));
    }

    @Operation(summary = "휴지통 조회")
    @GetMapping("/rubbish")
    public ResponseEntity<BaseResponseDTO<List<TodoListResponse>>> finRubbishTodoList(WebRequest webReq,
                                              HttpSession session) {
        return getResOK(webReq,
                BaseMessage.SUCCESS_OK, TodoListResponse.of(todoService.finRubbishTodoList()));
    }

    @Operation(summary = "할일 등록")
    @PostMapping
    public ResponseEntity<BaseResponseDTO<Object>> saveTodo(WebRequest webReq,
                                  HttpSession session,
                                  @RequestBody TodoRegisterRequest request) {
        request.validate();
        todoService.saveTodo(request.toDto());

        return getResOK(webReq, BaseMessage.SUCCESS_REGISTER);
    }

    @Operation(summary = "할일 수정")
    @PutMapping
    public ResponseEntity<BaseResponseDTO<Object>> modifyTodo(WebRequest webRequest,
                                       HttpSession session,
                                       @RequestBody TodoModifyInfoRequest request) {
        request.validate();
        todoService.modifyTodoInfo(request.toDto());

        return getResOK(webRequest, BaseMessage.SUCCESS_MODIFY);
    }

    @Operation(summary = "할일 상태 변경")
    @PatchMapping("/status")
    public ResponseEntity<BaseResponseDTO<Object>> modifyStatus(WebRequest webRequest,
                                        HttpSession session,
                                        @RequestBody TodoModifyStatusRequest request) {
        request.validate();
        todoService.modifyTodoStatus(request.getTodoMstId());

        return getResOK(webRequest, BaseMessage.SUCCESS_MODIFY);
    }

    @Operation(summary = "할일 삭제 - 휴지통")
    @DeleteMapping
    public ResponseEntity<BaseResponseDTO<Object>> modifyUseYn(WebRequest webRequest,
                                          HttpSession session,
                                          @RequestBody TodoModifyStatusRequest request) {
        request.validate();
        todoService.deleteTodo(request.getTodoMstId());

        return getResOK(webRequest, BaseMessage.SUCCESS_DELETE);
    }

    @Operation(summary = "할일 복구")
    @PatchMapping("/use")
    public ResponseEntity<BaseResponseDTO<Object>> restoreUseYn(WebRequest webRequest,
                                         HttpSession session,
                                         @RequestBody TodoModifyStatusRequest request) {
        request.validate();
        todoService.restoreTodo(request.getTodoMstId());

        return getResOK(webRequest, BaseMessage.SUCCESS_RESTORE);
    }
}
