package com.hdu.edu.creditcertificatesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * 系统入口
 *
 * @author chenyb46701
 * @date 2023/03/13
 */
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAspectJAutoProxy
public class CreditCertificateSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditCertificateSystemApplication.class, args);
        System.out.println("项目启动成功");
    }

}
