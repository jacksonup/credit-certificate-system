package com.hdu.edu.creditcertificatesystem.pojo.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 带泛型的响应类
 *
 * @author chenyb46701
 * @date 2023/3/13
 */
@Getter
@Setter
@ToString
public class BaseResponse {

    /**
     * 响应状态
     */
    private String code;

    /**
     * 响应状态说明
     */
    private String msg;


    public static final String SUCCESS_STATUS = "200";

    public static final String DISAPPEAR_STATUS = "404";
    public static final String FAIL_STATUS = "500";

}
