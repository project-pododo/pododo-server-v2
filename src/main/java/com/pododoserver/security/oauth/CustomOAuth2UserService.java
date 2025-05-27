package com.pododoserver.security.oauth;

import com.pododoserver.account.constant.Role;
import com.pododoserver.account.entity.AccountET;
import com.pododoserver.account.repository.AccountMstRepository;
import com.pododoserver.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AccountMstRepository accountMstRepository;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();
        Map<String,Object> attrs = oauthUser.getAttributes();

        // 프로바이더별 키 추출 예시 (구글)
        String providerId = attrs.get("sub").toString();
        String email      = attrs.get("email").toString();
        String name       = attrs.get("name").toString();

        // 회원 조회 또는 신규 생성
        AccountET account = accountMstRepository
                .findByProviderAndProviderId(provider, providerId)
                .orElseGet(() -> accountMstRepository.save(AccountET.builder()
                        .provider(provider)
                        .providerId(providerId)
                        .accountLoginId(email)
                        .accountEmail(email)
                        .accountName(name)
                        .role(Role.USER)
                        .build()));

        // JWT 미리 생성해두고, SuccessHandler로 전달하기 위해 authorities에 담아둡니다.
        String token = jwtTokenProvider.createToken(provider, providerId, account.getRole().name());

        return new DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority("ROLE_" + account.getRole().name())),
                Map.of("token", token),
                "token"  // getName()이 token 필드를 반환
        );
    }
}
