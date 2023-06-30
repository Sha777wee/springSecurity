package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 75722
 * @description 针对表【sys_role_menu】的数据库操作Mapper
 * @createDate 2023-06-29 22:33:23
 * @Entity com.example.domain.RoleMenu
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

}




