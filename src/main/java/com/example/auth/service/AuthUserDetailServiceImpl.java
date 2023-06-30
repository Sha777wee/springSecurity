package com.example.auth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.auth.SecurityUser;
import com.example.domain.User;
import com.example.service.MenuService;
import com.example.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author Shawee
 * @Date 2023/6/30
 */
@Service
public class AuthUserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("账号不存在");
        }
        List<String> authenList = menuService.queryPermissionsByUserId(user.getUserId());
        List<SimpleGrantedAuthority> grantedAuthorities = authenList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        SecurityUser securityUser = new SecurityUser(user);
        securityUser.setSimpleGrantedAuthorities(grantedAuthorities);
        return securityUser;
    }
}
