package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 机构信息实体类
 *
 * @author chenyb46701
 * @date 2023/4/24
 */
@Getter
@Setter
@ToString
public class InstitutionDTO {
    /**
     * 主键
     */
    private String id;

    /**
     * 机构名
     */
    private String institutionName;

    /**
     * 电话
     */
    private String institutionPhone;

    /**
     * 邮箱
     */
    private String institutionEmail;

    /**
     * 地址
     */
    private String institutionPlace;

    /**
     * 证明图片URL列表
     */
    private List<String> authorCertificatePic;

    /**
     * 创建时间
     */
    private String createTime;
}
