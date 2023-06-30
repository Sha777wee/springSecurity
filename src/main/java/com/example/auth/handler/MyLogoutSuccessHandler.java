package com.example.auth.handler;

import com.example.dto.HttpResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author Shawee
 * @Date 2023/6/30
 */

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorization)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            HttpResult httpResult = HttpResult.builder().code(-1).msg("token不能为空").build();
            PrintWriter writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(httpResult));
            writer.flush();
        }
        //如果Authorization信息不为空，去掉头部的Bearer字符串
        String token = authorization.replace("Bearer ", "");
        //redis中删除token，这是关键点
        stringRedisTemplate.delete("login-token:" + token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        HttpResult httpResult = HttpResult.builder().code(200).msg("退出成功").build();
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(httpResult));
        writer.flush();
    }
}
