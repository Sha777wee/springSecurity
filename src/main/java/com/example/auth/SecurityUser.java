package com.example.auth;

import com.example.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Author Shawee
 * @Date 2023/6/29
 */
@Data
public class SecurityUser implements UserDetails {

    private final User user;

    private List<SimpleGrantedAuthority> simpleGrantedAuthorities;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        String userPassword = this.user.getPassword();
        // 清除密码，防止传给前端
        this.user.setPassword(null);
        return userPassword;
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getAccountNoExpired().equals(1);
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getAccountNoLocked().equals(1);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getCredentialsNoExpired().equals(1);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
