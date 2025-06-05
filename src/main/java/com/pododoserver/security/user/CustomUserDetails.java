package com.pododoserver.security.user;

import com.pododoserver.account.entity.AccountET;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Long accountMstId;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;
    private final boolean enabled;

    public CustomUserDetails(AccountET account) {
        this.accountMstId   = account.getAccountMstId();
        this.username    = account.getAccountLoginId();
        this.password    = account.getAccountLoginPw();
        this.enabled     = !"Y".equals(account.getDeleteYn());
        this.authorities = List.of(new SimpleGrantedAuthority(account.getRole().name()));
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public String getPassword()                   { return password; }
    @Override public String getUsername()                   { return username; }
    @Override public boolean isAccountNonExpired()          { return true; }
    @Override public boolean isAccountNonLocked()           { return true; }
    @Override public boolean isCredentialsNonExpired()      { return true; }
    @Override public boolean isEnabled()                    { return enabled; }
}
