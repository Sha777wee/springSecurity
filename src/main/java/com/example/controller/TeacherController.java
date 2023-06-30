package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Shawee
 * @Date 2023/6/28
 */
@RestController
@RequestMapping("/teacher")
@PreAuthorize("hasAuthority('/teacher/**')")
public class TeacherController {
    
    @GetMapping("/query")
    @PreAuthorize("hasAuthority('teacher:query')")
    public String queryInfo() {
        return "success";
    }


}
