package com.pododoserver.security.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final String redirectUri;

    public OAuth2AuthenticationSuccessHandler(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // DefaultOAuth2User의 getName()이 token 값이 되도록 위에서 설정했음
        String token = authentication.getName();
        // URL fragment 방식으로 전달 (보안 고려해 선택)
        String targetUrl = redirectUri + "#token=" + token;
        response.sendRedirect(targetUrl);

    }
}
