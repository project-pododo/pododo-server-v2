package com.pododoserver.account.controller;

import com.pododoserver.account.controller.request.AccountLoginRequest;
import com.pododoserver.account.controller.request.AccountModifyRequest;
import com.pododoserver.account.controller.request.AccountRegisterRequest;
import com.pododoserver.account.controller.response.AccountInfoResponse;
import com.pododoserver.account.service.AccountService;
import com.pododoserver.common.BaseController;
import com.pododoserver.common.constant.BaseMessage;
import com.pododoserver.common.dto.BaseResponseDTO;
import com.pododoserver.security.jwt.controller.response.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/account")
public class AccountController extends BaseController {

    private final AccountService accountService;

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<BaseResponseDTO<JwtResponse>> login(WebRequest webRequest,
                                                            @RequestBody AccountLoginRequest request) {
        request.validate();

        JwtResponse jwtResponse = accountService.login(request.toDto());
        return getResOK(webRequest, BaseMessage.SUCCESS_OK, jwtResponse);
    }

    @Operation(summary = "회원가입")
    @PostMapping
    public ResponseEntity<BaseResponseDTO<Object>> register(WebRequest webReq,
                                                            @RequestBody AccountRegisterRequest request) {
        request.validate();
        accountService.registerAccount(request.toDto());
        return getResOK(webReq, BaseMessage.SUCCESS_REGISTER);
    }

    @Operation(summary = "회원정보 수정")
    @PutMapping
    public ResponseEntity<BaseResponseDTO<Object>> modify(WebRequest webReq,
                                                          @RequestBody AccountModifyRequest request) {
        request.validate();
        accountService.updateAccount(request.toDto());
        return getResOK(webReq, BaseMessage.SUCCESS_MODIFY);
    }

    @Operation(summary = "계정정보 조회")
    @GetMapping("/{accountMstId}")
    public ResponseEntity<BaseResponseDTO<AccountInfoResponse>> info(WebRequest webReq,
                                                        @PathVariable Long accountMstId) {
        return getResOK(webReq,
                BaseMessage.SUCCESS_OK, AccountInfoResponse.of(accountService.getAccountInfo(accountMstId)));
    }
}
