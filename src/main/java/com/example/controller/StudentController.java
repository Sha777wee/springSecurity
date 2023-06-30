package com.example.controller;

import com.example.constant.Constants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Shawee
 * @Date 2023/6/28
 */
@RestController
@RequestMapping("/student")
@PreAuthorize("hasAuthority('/student/**')")
public class StudentController {


    @GetMapping("/query")
    @PreAuthorize("hasAuthority('student:query')")
    public String queryInfo() {
        return Constants.SUCCESS;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('student:add')")
    public String addStudent() {
        return Constants.SUCCESS;
    }

    @GetMapping("/update")
    @PreAuthorize("hasAuthority('student:update')")
    public String updateStudent() {
        return Constants.SUCCESS;
    }

    @GetMapping("/del")
    @PreAuthorize("hasAuthority('student:del')")
    public String delStudent() {
        return Constants.SUCCESS;
    }

    @GetMapping("/export")
    @PreAuthorize("hasAuthority('student:export')")
    public String exportStudent() {
        return Constants.SUCCESS;
    }
}
