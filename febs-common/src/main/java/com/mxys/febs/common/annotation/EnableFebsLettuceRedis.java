package com.mxys.febs.common.annotation;

import com.mxys.febs.common.configure.FebsLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsLettuceRedisConfigure.class)
public @interface EnableFebsLettuceRedis {

}


