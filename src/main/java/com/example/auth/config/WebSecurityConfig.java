package com.example.auth.config;

import com.example.auth.filter.JwtCheckFilter;
import com.example.auth.handler.MyAuthenticationSuccessHandler;
import com.example.auth.handler.MyLogoutSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Author Shawee
 * @Date 2023/6/28
 */
@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Resource
    private JwtCheckFilter jwtCheckFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtCheckFilter, UsernamePasswordAuthenticationFilter.class);
        // 登录成功后调用handler返回jwt token，并在redis中缓存
        http.formLogin().successHandler(myAuthenticationSuccessHandler).permitAll();
        // 登出成功后调用handler删除redis缓存中的jwt token
        http.logout().logoutSuccessHandler(myLogoutSuccessHandler);
        http.authorizeRequests()
                .anyRequest().authenticated();
        http.csrf().disable();

        // 使用了jwt来进行会话跟踪，就可以禁用session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}