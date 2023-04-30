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
     *
     */
    public String id;

    /**
     * 机构名
     */
    public String institutionName;

    /**
     * 电话
     */
    public String institutionPhone;

    /**
     * 邮箱
     */
    public String institutionEmail;

    /**
     * 地址
     */
    public String institutionPlace;

    /**
     * 证明图片URL列表
     */
    public List<String> authorCertificatePic;
}
