package com.pododoserver.account.controller;

import com.pododoserver.account.controller.request.AccountLoginRequest;
import com.pododoserver.account.controller.request.AccountModifyRequest;
import com.pododoserver.account.controller.request.AccountRegisterRequest;
import com.pododoserver.account.controller.response.AccountInfoResponse;
import com.pododoserver.account.service.AccountService;
import com.pododoserver.common.BaseController;
import com.pododoserver.common.constant.BaseMessage;
import com.pododoserver.common.dto.BaseResponseDTO;
import com.pododoserver.security.jwt.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/account")
public class AccountController extends BaseController {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDTO<Map<String,String>>> login(WebRequest webRequest,
            @RequestBody AccountLoginRequest request) {

        request.validate();

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        request.getAccountLoginId(), request.getAccountLoginPw()
                );

        Authentication auth = authManager.authenticate(authToken);
        String token = jwtTokenProvider.generateToken(auth);

        Map<String,String> data = Map.of("accessToken", token);
        return getResOK(webRequest, BaseMessage.SUCCESS_OK, data);
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
             @PathVariable Long accountMstId, @AuthenticationPrincipal UserDetails user) {
        log.info(user.getUsername());
        return getResOK(webReq,
                BaseMessage.SUCCESS_OK, AccountInfoResponse.of(accountService.getAccountInfo(accountMstId)));
    }
}
