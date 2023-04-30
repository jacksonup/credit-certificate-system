package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Setter;
import lombok.ToString;

/**
 * 通用DTO
 *
 * @author chenyb46701
 * @date 2023/4/12
 */
@Setter
@ToString
public class BaseDTO {
    /**
     * 登录令牌
     */
    private String token;
}
