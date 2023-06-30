package com.example.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author Shawee
 * @Date 2023/6/29
 */
@Data
@Builder
public class HttpResult {
    private Integer code;

    private String msg;

    private Object data;
}
