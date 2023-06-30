package com.example.controller;

import com.example.constant.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Shawee
 * @Date 2023/6/28
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/query")
    public String queryInfo() {
        return Constants.SUCCESS;
    }
}
