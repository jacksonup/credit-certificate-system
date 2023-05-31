package com.hdu.edu.creditcertificatesystem.spring;

import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 服务组件切面实现
 *
 * @author chenyb46701
 * @date 2023/4/18
 */
@Aspect
@Slf4j
@Component
public class CloudComponentAdvice {

    @Pointcut("@within(com.hdu.edu.creditcertificatesystem.spring.CloudComponent)")
    public void rpcPointcut() {
    }

    @Around("rpcPointcut()")
    public Object rpcHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        final Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BaseRequest) {
                BaseRequest baseRequest = (BaseRequest) arg;

                // 校验token是否有效
                if (StringUtils.isNotBlank(baseRequest.getToken()) && !JwtUtils.verifyToken(baseRequest.getToken())) {
                    throw new BaseException(ErrorCodeConstant.PARAM_VALID_CODE, "token失效");
                }
            }
        }
        return joinPoint.proceed();
    }
}
