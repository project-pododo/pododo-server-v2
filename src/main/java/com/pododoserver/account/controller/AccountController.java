package com.pododoserver.account.controller;

import com.pododoserver.account.controller.request.OAuthRequest;
import com.pododoserver.account.dto.GoogleOAuthDto;
import com.pododoserver.common.BaseController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/account")
public class AccountController extends BaseController {


    @PostMapping("/oauth/google")
    public ResponseEntity<?> googleOAuth2Login(@RequestBody OAuthRequest request) {
        GoogleOAuthDto dto = new GoogleOAuthDto(request.getCode(), request.getState());
        return null;
    }
}
