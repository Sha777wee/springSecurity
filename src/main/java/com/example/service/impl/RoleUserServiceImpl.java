package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.RoleUser;
import com.example.service.RoleUserService;
import com.example.mapper.RoleUserMapper;
import org.springframework.stereotype.Service;

/**
* @author 75722
* @description 针对表【sys_role_user】的数据库操作Service实现
* @createDate 2023-06-29 22:33:23
*/
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser>
    implements RoleUserService{

}




