package com.mxys.febs.common.configure;

import com.mxys.febs.common.interceptor.FebsServerProtectInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 我们在该配置类里注册了FebsServerProtectInterceptor，并且将它加入到了Spring的拦截器链中
 */
public class FebsServerProtectConfigure implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor febsServerProtectInterceptor() {
        return new FebsServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(febsServerProtectInterceptor());
    }
}