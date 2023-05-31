package com.hdu.edu.creditcertificatesystem.pojo.dto.mail;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 待告警内容
 *
 * @author zuowei44676
 * @version 1.0
 * @date 2022/9/17
 **/
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class AlarmContent implements Serializable {
    /**
     * 审核情况-是否通过
     */
    private Boolean isPass;

    /**
     * 驳回原因
     */
    private String rejectReason;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;
}
