package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 教师信息DTO
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
@Getter
@Setter
@ToString
public class TeacherInfoDTO {
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
    private String sexual;

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
     * 学历
     */
    private String educationalBg;

    /**
     * 电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;
}
