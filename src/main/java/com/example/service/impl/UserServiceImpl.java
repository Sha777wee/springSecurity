package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.User;
import com.example.service.UserService;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 75722
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-06-29 22:33:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




