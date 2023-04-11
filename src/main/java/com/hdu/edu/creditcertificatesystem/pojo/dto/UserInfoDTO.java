package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息DTO
 *
 * @author chenyb46701
 * @date 2023/4/3
 */
@Getter
@Setter
@ToString
public class UserInfoDTO {
    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
