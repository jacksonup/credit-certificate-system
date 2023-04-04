package com.hdu.edu.creditcertificatesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


/**
 * 系统入口
 *
 * @author chenyb46701
 * @date 2023/03/13
 */
@SpringBootApplication
@ConfigurationPropertiesScan
@MapperScan("com.hdu.edu.creditcertificatesystem")
public class CreditCertificateSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditCertificateSystemApplication.class, args);
    }

}
