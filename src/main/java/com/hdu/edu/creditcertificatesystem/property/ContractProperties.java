package com.hdu.edu.creditcertificatesystem.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigInteger;

/**
 * 合约配置类
 *
 * @author chenyb46701
 * @date 2023/4/3
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "contract")
public class ContractProperties {
    /**
     * 监听本地ganache地址
     */
    private String httpService;

    /**
     * 部署合约账号地址
     */
    private String credentials;

    /**
     * 油价
     */
    private BigInteger gasPrice;

    /**
     * 单次交易油个数限制
     */
    private BigInteger gasLimit;
}
