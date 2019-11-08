package com.mxys.febs.server.system.configure;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis Plus分页插件配置
 */
@Configuration
public class FebsWebConfigure {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor interceptor=new PaginationInterceptor();
        List<ISqlParser> iSqlParsers=new ArrayList<>();

        iSqlParsers.add(new BlockAttackSqlParser());
        interceptor.setSqlParserList(iSqlParsers);
        return interceptor;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
