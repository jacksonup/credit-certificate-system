package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * token 实体
 *
 * @author chenyb46701
 * @date 2023/3/13
 */
@Getter
@Setter
@ToString
public class TokenDTO {
    /**
     * 账号
     */
    private String account;

    /**
     * 角色
     */
    private Integer role;

    /**
     * 时间
     */
    private String time;
}
