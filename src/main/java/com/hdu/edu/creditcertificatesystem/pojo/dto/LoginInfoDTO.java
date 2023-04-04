package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录信息DTO
 *
 * @author chenyb46701
 * @date 2023/4/3
 */
@Getter
@Setter
@ToString
public class LoginInfoDTO {
    /**
     * 登录令牌
     */
    private String token;

    /**
     * 角色
     */
    private String role;
}
