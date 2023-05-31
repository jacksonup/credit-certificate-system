package com.hdu.edu.creditcertificatesystem.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 邮件配置
 *
 * @author zuowei44676
 * @version 1.0
 * @date 2022/9/19
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "system.mail")
public class MailProperties {

    /**
     * 邮箱主机
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 用户名
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱服务器类型(微软Exchange服务器填写ms_exchange，特殊处理)，非必填
     */
    private String serverType;

    /**
     * 发送协议，一般发送邮件的协议是smtp
     */
    private String protocol;

    /**
     * 使用账号和密码认证
     */
    private String auth = "true";

    /**
     * 是否ssl
     */
    private boolean ssl;

    /**
     * 安全协议类型即版本，非必填，默认为TLSv1.0s
     */
    private String protocols;

    /**
     * 编码
     */
    private String encoding;
}
