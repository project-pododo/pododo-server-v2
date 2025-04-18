package com.pododoserver.account.controller;

import com.pododoserver.account.dto.SignUpRequest;
import com.pododoserver.common.BaseController;
import com.pododoserver.common.constant.BaseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController extends BaseController {


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(WebRequest webRequest,
                                    @RequestBody SignUpRequest request) {

        log.info("request = " + request.toString());
        request.validate();

        return getResOK(webRequest, BaseMessage.SUCCESS_REGISTER);
    }
}
