package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Menu;

import java.util.List;

/**
 * @author 75722
 * @description 针对表【sys_menu】的数据库操作Service
 * @createDate 2023-06-29 22:33:23
 */
public interface MenuService extends IService<Menu> {
    List<String> queryPermissionsByUserId(Integer userId);
}
