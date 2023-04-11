package com.hdu.edu.creditcertificatesystem.spring;

import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 合约加载注解
 *
 * @author chenyb46701
 * @date 2023/4/10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContractLoader {
    ContractTypeEnum[] value();
}
