package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Menu;
import com.example.mapper.MenuMapper;
import com.example.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 75722
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2023-06-29 22:33:23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<String> queryPermissionsByUserId(Integer userId) {
        return menuMapper.queryPermissionsByUserId(userId);
    }
}




