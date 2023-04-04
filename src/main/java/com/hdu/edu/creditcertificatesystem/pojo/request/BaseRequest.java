package com.hdu.edu.creditcertificatesystem.pojo.request;

import lombok.Getter;

/**
 * 通用请求类
 *
 * @author chenyb46701
 * @date 2023/4/3
 */
@Getter
public class BaseRequest {
    /**
     * 用户令牌
     */
    private String token;
}
