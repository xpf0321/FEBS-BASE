package com.mxys.febs.common.annotation;

import com.mxys.febs.common.configure.FebsServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsServerProtectConfigure.class)
public @interface EnableFebsServerProtect {
}
