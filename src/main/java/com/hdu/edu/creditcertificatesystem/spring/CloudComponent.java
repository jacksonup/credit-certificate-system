package com.hdu.edu.creditcertificatesystem.spring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务组件注解
 *
 * @author chenyb46701
 * @date 2023/4/18
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CloudComponent {
    String value() default "";
}
