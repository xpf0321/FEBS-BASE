package com.mxys.febs.auth.service;

import com.mxys.febs.auth.manager.UserManager;
import com.mxys.febs.common.entity.FebsAuthUser;
import com.mxys.febs.common.entity.system.SystemUser;
import io.micrometer.core.instrument.binder.BaseUnits;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FebsUserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = userManager.findByName(username);
        if(user!=null){
            String permissions=userManager.findUserPermissions(username);
            boolean notLocked=false;
            if(SystemUser.STATUS_VALID.equals(user.getStatus())){
                notLocked=true;
            }
            FebsAuthUser authUser=new FebsAuthUser(user.getUsername(),user.getPassword(),true,
                    true,true,notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            BeanUtils.copyProperties(user,authUser);//transSystemUserToAuthUser(authUser,user);
            return authUser;
        }else{
            throw new UsernameNotFoundException("");
        }
    }
    /**
     * 上面主要的逻辑就是通过用户名从数据库中获取用户信息SystemUser和用户权限集合，
     * 然后通过transSystemUserToAuthUser方法将SystemUser里的值拷贝到FebsAuthUser中并返回。
     * 两个实体类值的拷贝Spring给我们提供了相应的工具类，上面的代码可以简化
     */
   /* private FebsAuthUser transSystemUserToAuthUser(FebsAuthUser authUser, SystemUser systemUser) {
        authUser.setAvatar(systemUser.getAvatar());
        authUser.setDeptId(systemUser.getDeptId());
        authUser.setDeptName(systemUser.getDeptName());
        authUser.setEmail(systemUser.getEmail());
        authUser.setMobile(systemUser.getMobile());
        authUser.setRoleId(systemUser.getRoleId());
        authUser.setRoleName(systemUser.getRoleName());
        authUser.setSex(systemUser.getSex());
        authUser.setUserId(systemUser.getUserId());
        authUser.setLastLoginTime(systemUser.getLastLoginTime());
        authUser.setDescription(systemUser.getDescription());
        authUser.setStatus(systemUser.getStatus());
        return authUser;
    }*/

}