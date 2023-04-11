package com.hdu.edu.creditcertificatesystem.spring;

import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.property.ContractProperties;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * 加载合约的切面
 *
 * @author chenyb46701
 * @date 2023/4/10
 */
@Aspect
@Component
@Slf4j
public class ContractLoaderAdvice {
    @Setter(onMethod_ = @Autowired)
    private ContractProperties contractProperties;

    @Setter(onMethod_ = @Resource(name = "stringRedisTemplate"))
    private ValueOperations<String, String> redisValueOperations;

    private UserContract userContract;

    /**
     * 定义一个使用了ContractLoader注解的切点
     */
    @Pointcut("@within(com.hdu.edu.creditcertificatesystem.spring.ContractLoader)")
    public void contractLoaderPointcut() {
    }

    /**
     * 拦截所有在类上使用了切点的切面
     *
     * @param joinPoint 切入点
     * @return 对象
     * @throws Throwable 异常
     */
    @Around("contractLoaderPointcut()")
    public Object loadContract(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("正在加载合约的切面...");
        Object target = joinPoint.getTarget();
        ContractLoader contractLoader = target.getClass().getAnnotation(ContractLoader.class);

        if (contractLoader != null) {

            // 监听本地链
            Web3j web3j = Web3j.build(new HttpService(contractProperties.getHttpService()));

            // 生成资格凭证
            Credentials credentials = Credentials.create(contractProperties.getCredentials());

            StaticGasProvider provider = new StaticGasProvider(
                    contractProperties.getGasPrice(),
                    contractProperties.getGasLimit());

            // 获取被代理的 Service 实例
            Object serviceTarget = joinPoint.getTarget();
            for (ContractTypeEnum typeEnum : contractLoader.value()) {
                switch (typeEnum) {
                    case USER:
                        log.info("正在加载用户合约...");
                        // 获取用户合约的地址
                        final String address = redisValueOperations.get(ContractTypeEnum.USER.getValue());
                        if (StringUtils.isBlank(address)) {
                            log.error("用户合约未部署!");
                            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "用户合约未部署!");
                        }

                        // 获取该实例中的 userContract 字段
                        Field userContractField = serviceTarget.getClass().getDeclaredField(ContractTypeEnum.USER.getValue());

                        // 解除私有字段的限制
                        userContractField.setAccessible(true);

                        // 设置该字段的值为合约实例
                        userContractField.set(serviceTarget, UserContract.load(address, web3j, credentials, provider));
                        log.info("加载用户合约成功!");
                        break;
                    case TEACHER:
                        // 加载教师合约
                    default:
                }
            }
        }


        return joinPoint.proceed();
    }
}
