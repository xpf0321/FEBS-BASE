package com.mxys.febs.server.test;

import com.mxys.febs.common.annotation.EnableFebsAuthExceptionHandler;
import com.mxys.febs.common.annotation.EnableFebsOauth2FeignClient;
import com.mxys.febs.common.annotation.EnableFebsServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableFebsAuthExceptionHandler
@EnableFeignClients
@EnableFebsOauth2FeignClient
@EnableFebsServerProtect
public class FebsServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsServerTestApplication.class, args);
    }

}
