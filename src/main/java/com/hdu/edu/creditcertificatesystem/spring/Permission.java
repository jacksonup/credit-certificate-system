package com.hdu.edu.creditcertificatesystem.spring;

import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 *
 * @author chenyb46701
 * @date 2023/4/23
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    RolePermissionEnum[] role();
}
