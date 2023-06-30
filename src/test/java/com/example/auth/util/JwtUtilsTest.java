package com.example.auth.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

/**
 * @Author Shawee
 * @Date 2023/6/30
 */

@Slf4j
public class JwtUtilsTest {

    @Test
    public void encode() {
        String rawString = "123456";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodeString = encoder.encode(rawString);
        log.info(encodeString);
    }

    @Test
    void match() {
        String rawString = "123456";
        String encodeString = "$2a$10$Ok.wToKxqMRQxthStqO41Ok3p2gMTRNYiKqjcjENZrxA4iE/3i7Ri";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Assert.isTrue(encoder.matches(rawString, encodeString));
    }

}
