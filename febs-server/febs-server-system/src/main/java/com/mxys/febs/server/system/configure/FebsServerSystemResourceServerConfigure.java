package com.mxys.febs.server.system.configure;

import com.mxys.febs.common.handler.FebsAccessDeniedHandler;
import com.mxys.febs.common.handler.FebsAuthExceptionEntryPoint;
import com.mxys.febs.server.system.properties.FebsServerSystemProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class FebsServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {
    @Autowired
    private FebsAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private FebsAuthExceptionEntryPoint exceptionEntryPoint;

    @Autowired
    private FebsServerSystemProperties properties;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        String [] url= StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(),",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests().antMatchers(url).permitAll()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(exceptionEntryPoint);
    }
}