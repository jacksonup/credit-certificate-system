package com.hdu.edu.creditcertificatesystem.pojo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

/**
 * 教师信息请求
 *
 * @author chenyb46701
 * @date 2023/5/23
 */
@Getter
@Setter
@ToString
public class TeacherInfoRequest extends BaseRequest {
    /**
     * 学号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sexual;

    /**
     * 职位
     */
    private String position;

    /**
     * 职务
     */
    private String duty;

    /**
     * 政治面貌
     */
    private String politicalOutlook;

    /**
     * 部门
     */
    private String department;

    /**
     * 出生日期yyyy-MM-dd
     */
    private String birthday;

    /**
     * 任职时间yyyy-MM-dd
     */
    private String duration;

    /**
     * 证件照路径
     */
    private String IDPhoto;
}
