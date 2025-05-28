package com.pododoserver.security.user;


import com.pododoserver.account.entity.AccountET;
import com.pododoserver.account.repository.AccountMstRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {


    private final AccountMstRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AccountET e = repo.findByAccountLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 계정이 없습니다."));
        return User.builder()
                .username(e.getAccountLoginId())
                .password(e.getAccountLoginPw())   // 암호화된 비밀번호
                .roles(e.getRole().name())
                .build();
    }
}
