package com.hdu.edu.creditcertificatesystem.pojo.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息请求类
 *
 * @author chenyb46701
 * @date 2023/3/31
 */
@Getter
@Setter
public class UserInfoRequest extends BaseRequest {
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
     * 创建时间
     */
    private Integer createTime;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
