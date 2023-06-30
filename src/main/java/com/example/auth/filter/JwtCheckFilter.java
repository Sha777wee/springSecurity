package com.example.auth.filter;

import com.example.auth.SecurityUser;
import com.example.auth.util.JwtUtils;
import com.example.domain.User;
import com.example.dto.HttpResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Shawee
 * @Date 2023/6/29
 */
@Component
@Slf4j
public class JwtCheckFilter extends OncePerRequestFilter {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 登录页面放行
        String requestUri = request.getRequestURI();
        if ("/login".equals(requestUri)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 判断请求头中的jwt token
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorization)) {
            printFront(response, "没有登录！");
            return;
        }
        String jwtToken = authorization.replace("Bearer", "");

        // 从redis中获取token
        String tokenInRedis = stringRedisTemplate.opsForValue().get("login-token:" + jwtToken);
        if (!StringUtils.hasText(tokenInRedis)) {
            printFront(response, "用户已退出，请重新登录");
            return;
        }

        boolean verifyTokenResult = jwtUtils.verifyToken(jwtToken);
        if (!verifyTokenResult) {
            printFront(response, "jwtToken 验证失败！");
            return;
        }

        // 获取用户信息
        String userInfo = jwtUtils.getUserInfo(jwtToken);
        User user = objectMapper.readValue(userInfo, User.class);
        SecurityUser securityUser = new SecurityUser(user);
        List<String> authList = objectMapper.readValue(jwtUtils.getUserAuth(jwtToken), List.class);
        List<SimpleGrantedAuthority> authorityList = authList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(securityUser, null, authorityList);
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }

    private void printFront(HttpServletResponse response, String message) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        HttpResult httpResult = HttpResult.builder().code(-1).msg("failed").data(message).build();
        writer.print(objectMapper.writeValueAsString(httpResult));
        writer.flush();
    }
}
