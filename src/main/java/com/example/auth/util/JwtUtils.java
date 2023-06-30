package com.example.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 *
 * @Author Shawee
 * @Date 2023/6/29
 */
@Component
@Slf4j
public class JwtUtils {
    @Value("${jwt.issuer}")
    private String issuer;

    @Value("#{T(java.lang.Integer).parseInt('${jwt.liveTime}')}")
    private Integer liveTime;

    // 算法密钥
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    /**
     * 创建jwt token
     *
     * @param userInfo 用户信息json字符串
     * @param authList 权限列表json字符串
     * @return
     */
    public String createToken(String userInfo, String authList) {
        Date currentTime = new Date();
        Date expireTime = new Date(currentTime.getTime() + liveTime);
        // 组装jwt头部
        Map<String, Object> header = new HashMap<>();
        header.put("type", "JWT");
        header.put("alg", "HS256");

        return JWT.create()
                .withHeader(header)
                .withIssuedAt(currentTime)
                .withExpiresAt(expireTime)
                .withIssuer(issuer)
                .withClaim("userInfo", userInfo)
                .withClaim("authList", authList)
                .sign(Algorithm.HMAC256(jwtSecretKey));
    }

    /**
     * 验证jwt token
     *
     * @param token
     * @return
     */
    public boolean verifyToken(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtSecretKey)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error("验签失败，{}", token, e);
        }
        return true;
    }

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    public String getUserInfo(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtSecretKey)).build();
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getClaim("userInfo").asString();
        } catch (JWTVerificationException e) {
            log.error("验签失败，{}", token, e);
        }
        return null;
    }

    /**
     * 获取用户权限
     */
    public String getUserAuth(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtSecretKey)).build();
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getClaim("authList").asString();
        } catch (JWTVerificationException e) {
            log.error("验签失败，{}", token, e);
        }
        return null;
    }
}
