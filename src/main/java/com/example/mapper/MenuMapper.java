package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 75722
 * @description 针对表【sys_menu】的数据库操作Mapper
 * @createDate 2023-06-29 22:33:23
 * @Entity com.example.domain.Menu
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> queryPermissionsByUserId(@Param("userId") Integer userId);
}




