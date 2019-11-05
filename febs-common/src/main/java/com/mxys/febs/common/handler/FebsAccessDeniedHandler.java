package com.mxys.febs.common.handler;

import com.mxys.febs.common.entity.FebsResponse;
import com.mxys.febs.common.utils.FebsUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FebsAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * 响应码为HttpServletResponse.SC_FORBIDDEN，即403。
     * 因为febs-common模块是一个普通的maven项目，并不是一个Spring Boot项目，
     * 所以即使在这两个类上使用@Component注解标注，
     * 它们也不能被成功注册到各个微服务子系统的Spring IOC容器中。我们可以使用@Enable模块驱动的方式来解决这个问题。
     * */


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        FebsResponse febsResponse = new FebsResponse();
        FebsUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_FORBIDDEN, febsResponse.message("没有权限访问该资源"));
    }
}