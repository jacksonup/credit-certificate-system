package com.hdu.edu.creditcertificatesystem.pojo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;

/**
 * 机构信息请求类
 *
 * @author chenyb46701
 * @date 2023/4/24
 */
@Getter
@Setter
@ToString
public class InstitutionRequest extends BaseRequest {
    /**
     * 主键Id
     */
    private String id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 机构名
     */
    private String institutionName;

    /**
     * 机构电话号码
     */
    private String institutionPhone;

    /**
     * 机构邮箱
     */
    private String institutionEmail;

    /**
     * 机构地址
     */
    private String institutionPlace;

    /**
     * 审核状态；0待审核；1已通过；
     */
    private Integer status;

    /**
     * 授权证明照片
     */
    private String authorCertificatePic;

    /**
     * 专业Id列表
     */
    private String majorId;

    /**
     * 学院Id列表
     */
    private String facultyId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 审核时间
     */
    private String auditTime;

    /**
     * 留言
     */
    private String message;

    /**
     * 驳回原因
     */
    private String reason;

    /* 扩展字段 */

    /**
     * 证明图片URL列表
     */
    private List<String> proofPictures;

    /**
     * 学院id列表
     */
    private List<Long> faculties;

    /**
     * 专业id列表
     */
    private List<Long> majors;
}
