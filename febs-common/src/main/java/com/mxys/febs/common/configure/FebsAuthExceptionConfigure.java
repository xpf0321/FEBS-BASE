package com.mxys.febs.common.configure;

import com.mxys.febs.common.handler.FebsAccessDeniedHandler;
import com.mxys.febs.common.handler.FebsAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * 在该配置类中，我们注册了FebsAccessDeniedHandler和FebsAuthExceptionEntryPoint。@ConditionalOnMissingBean注解的意思是，当IOC容器中没有指定名称或类型的Bean的时候，就注册它。以@ConditionalOnMissingBean(name = "accessDeniedHandler")为例，当微服务系统的Spring IOC容器中没有名称为accessDeniedHandler的Bean的时候，就将FebsAccessDeniedHandler注册为一个Bean。
 * 这样做的好处在于，子系统可以自定义自个儿的资源服务器异常处理器，覆盖我们在febs-common通用模块里定义的。
 */
public class FebsAuthExceptionConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public FebsAccessDeniedHandler accessDeniedHandler() {
        return new FebsAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public FebsAuthExceptionEntryPoint authenticationEntryPoint() {
        return new FebsAuthExceptionEntryPoint();
    }
}