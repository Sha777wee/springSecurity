package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Role;
import com.example.service.RoleService;
import com.example.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 75722
* @description 针对表【sys_role】的数据库操作Service实现
* @createDate 2023-06-29 22:33:23
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




