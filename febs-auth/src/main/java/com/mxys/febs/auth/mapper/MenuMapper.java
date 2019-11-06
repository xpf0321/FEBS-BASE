package com.mxys.febs.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxys.febs.common.entity.system.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 用于通过用户名查找用户权限集合。
     * @param username
     * @return
     */
    List<Menu> findUserPermissions(String username);
}
