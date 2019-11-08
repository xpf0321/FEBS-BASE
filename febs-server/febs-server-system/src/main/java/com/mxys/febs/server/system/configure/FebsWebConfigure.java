package com.mxys.febs.server.system.configure;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.mxys.febs.server.system.properties.FebsServerSystemProperties;
import com.mxys.febs.server.system.properties.FebsSwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * MyBatis Plus分页插件配置
 */
@Configuration
@EnableSwagger2
public class FebsWebConfigure {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor interceptor=new PaginationInterceptor();
        List<ISqlParser> iSqlParsers=new ArrayList<>();

        iSqlParsers.add(new BlockAttackSqlParser());
        interceptor.setSqlParserList(iSqlParsers);
        return interceptor;
    }

    @Autowired
    private FebsServerSystemProperties properties;

    @Bean
    public Docket swaggerApi(){
        FebsSwaggerProperties swagger=properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any()).build().apiInfo(apiInfo(swagger))
                .securitySchemes(Collections.singletonList(securityScheme(swagger)))
                .securityContexts(Collections.singletonList(securityContext(swagger)));
    }

    public ApiInfo apiInfo( FebsSwaggerProperties swagger){
        return  new ApiInfo(swagger.getTitle(),swagger.getDescription(),swagger.getVersion(),null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }

    private SecurityScheme securityScheme(FebsSwaggerProperties swagger) {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(swagger.getGrantUrl());

        return new OAuthBuilder()
                .name(swagger.getName())
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes(swagger)))
                .build();
    }

    private SecurityContext securityContext(FebsSwaggerProperties swagger) {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(swagger.getName(), scopes(swagger))))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes(FebsSwaggerProperties swagger) {
        return new AuthorizationScope[]{
                new AuthorizationScope(swagger.getScope(), "")
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
