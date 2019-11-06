package com.mxys.febs.auth;

import com.mxys.febs.common.annotation.EnableFebsAuthExceptionHandler;
import com.mxys.febs.common.annotation.EnableFebsLettuceRedis;
import com.mxys.febs.common.annotation.EnableFebsServerProtect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFebsAuthExceptionHandler
@EnableFebsServerProtect
@EnableFebsLettuceRedis
@MapperScan("com.mxys.febs.auth.mapper")//作用为将路径下的Mapper类都注册到IOC容器中。
public class FebsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsAuthApplication.class, args);
    }

}
