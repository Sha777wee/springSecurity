package com.example.auth.handler;

import com.example.auth.SecurityUser;
import com.example.auth.util.JwtUtils;
import com.example.dto.HttpResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 认证成功处理器
 *
 * @Author Shawee
 * @Date 2023/6/29
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    // 序列化工具
    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取用户信息和权限列表
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String userInfo = objectMapper.writeValueAsString(securityUser.getUser());
        List<SimpleGrantedAuthority> authorityList = (List<SimpleGrantedAuthority>) securityUser.getAuthorities();
        List<String> roleList = authorityList.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toList());
        String authList = objectMapper.writeValueAsString(roleList);

        // 创建jwt token
        String token = jwtUtils.createToken(userInfo, authList);

        // 认证成功后存储到redis中
        stringRedisTemplate.opsForValue().set("login-token:" + token, objectMapper.writeValueAsString(authentication), 30, TimeUnit.MINUTES);

        // 返回客户端jwt token
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpResult httpResult = HttpResult.builder().code(200).msg("success").data(token).build();
        PrintWriter printWriter = response.getWriter();
        printWriter.write(objectMapper.writeValueAsString(httpResult));
        printWriter.flush();
    }
}
