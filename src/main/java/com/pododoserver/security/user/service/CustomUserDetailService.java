package com.pododoserver.security.user.service;


import com.pododoserver.account.entity.AccountET;
import com.pododoserver.account.repository.AccountMstRepository;
import com.pododoserver.security.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {


    private final AccountMstRepository accountMstRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountET account = accountMstRepository.findByAccountLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자["+username+"]를 찾을 수 없습니다."));
        return new CustomUserDetails(account);
    }
}
