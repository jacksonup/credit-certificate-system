package com.hdu.edu.creditcertificatesystem;

import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.property.ContractProperties;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Slf4j
class CreditCertificateSystemApplicationTests {
    @Setter(onMethod_ = @Autowired)
    private ContractProperties contractProperties;

    @Setter(onMethod_ = @Resource(name = "stringRedisTemplate"))
    private ValueOperations<String, String> redisValueOperations;

    /**
     * 部署合约
     * ganache不关闭，可以不用重新部署；ganache 重启需部署
     * ganache-cli -d "tackle frozen poet aware struggle ridge february merge pulse doll enhance air"
     *
     * @throws Exception 异常
     */
    @Test
    void contextLoads() throws Exception {
        // 监听本地链
        System.out.println(contractProperties.getHttpService());
        Web3j web3j = Web3j.build(new HttpService(contractProperties.getHttpService()));

        // 生成资格凭证
        Credentials credentials = Credentials.create(contractProperties.getCredentials());

        StaticGasProvider provider = new StaticGasProvider(
                contractProperties.getGasPrice(),
                contractProperties.getGasLimit());

        // 部署合约
        UserContract userContract = UserContract.deploy(web3j, credentials, provider).send();
        // 存储合约地址到redis中
        redisValueOperations.set(ContractTypeEnum.USER.getValue(), userContract.getContractAddress());

        // 创建初始化管理员账户

        // 获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        for (int i = 0; i < 5; i++) {
            UserContract.UserInfo userInfo = new UserContract.UserInfo(
                   "admin" + i,
                   "admin" + i,
                    new BigInteger("0"),
                    new BigInteger(localDateTime.format(formatter)),
                    "",
                    ""
            );
            userContract.save(userInfo).send();
        }
        log.info("合约部署成功!");
    }
}
