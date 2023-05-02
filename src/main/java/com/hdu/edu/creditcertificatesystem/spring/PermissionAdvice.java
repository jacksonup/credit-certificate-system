package com.hdu.edu.creditcertificatesystem.spring;

import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 权限切面
 *
 * @author chenyb46701
 * @date 2023/4/25
 */
@Aspect
@Component
@Slf4j
public class PermissionAdvice {
    @Pointcut("@annotation(com.hdu.edu.creditcertificatesystem.spring.Permission)")
    public void rolePointCut() {
    }

    @Before("rolePointCut()")
    public void checkRolePermission(JoinPoint joinPoint) {
        log.info("正在进行角色权限校验");

        final Object[] args = joinPoint.getArgs();
        int role = -1;
        boolean flag = false;
        for (Object obj : args) {
            if (obj instanceof BaseRequest) {
                BaseRequest baseRequest = (BaseRequest) obj;
                if (StringUtils.isNotBlank(baseRequest.getToken())) {
                    role = JwtUtils.getTokenInfo(baseRequest.getToken()).getClaim("role").asInt();
                }
            }
        }

        // 获取方法上注解的属性
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Permission permission = signature.getMethod().getAnnotation(Permission.class);
        if (permission != null) {
            // 获取角色列表
            final RolePermissionEnum[] roleEnums = permission.role();
            for (RolePermissionEnum rolePermissionEnum : roleEnums) {
                if (rolePermissionEnum.getKey() == role) {
                    flag = true;
                    break;
                }
            }
        }

        if (!flag) {
            log.error("角色权限不足");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "角色权限不足");
        }
    }
}
