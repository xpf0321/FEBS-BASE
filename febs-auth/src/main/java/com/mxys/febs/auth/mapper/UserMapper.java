package com.mxys.febs.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxys.febs.common.entity.system.SystemUser;

public interface UserMapper extends BaseMapper<SystemUser> {

    /**
     * 用于通过用户名查找用户信息。
     * @param username
     * @return
     */
    SystemUser findByName(String username);

}
