package com.mxys.febs.server.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.mxys.febs.common.entity.FebsResponse;
import com.mxys.febs.common.entity.QueryRequest;
import com.mxys.febs.common.entity.system.SystemUser;
import com.mxys.febs.common.exception.FebsException;
import com.mxys.febs.common.utils.FebsUtil;
import com.mxys.febs.server.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('user:view')")
    public FebsResponse userList(QueryRequest queryRequest, SystemUser user){
        Map<String ,Object> dateTable= FebsUtil.getDataTable(userService.findUserDetail(user,queryRequest));
        return new FebsResponse().data(dateTable);
    }


    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')")
    public void userAdd(@Valid SystemUser user){

        try {
            this.userService.createUser(user);
        } catch (Exception e) {
            String message="新建用户失败";
            log.error(message,e);
            new FebsException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public void updateUser(@Valid SystemUser user) throws FebsException {
        try {
            this.userService.updateUser(user);
        } catch (Exception e) {
            String message = "修改用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @DeleteMapping("/{userIds}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws FebsException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
        } catch (Exception e) {
            String message = "删除用户失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
