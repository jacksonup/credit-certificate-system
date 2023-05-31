package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


/**
 * 学生信息DTO
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
@Getter
@Setter
@ToString
public class StudentInfoDTO {
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
     * 籍贯
     */
    private String nativePlace;

    /**
     * 院系
     */
    private String department;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    private String grade;

    /**
     * 学历
     */
    private String educationBg;

    /**
     * 担任职务
     */
    private String position;

    /**
     * 政治面貌
     */
    private String politicalOutlook;

    /**
     * 证件照路径
     */
    private String IDPhoto;

    /**
     * 生源地
     */
    private String birthPlace;

    /**
     * 出生日期yyyy-MM-dd
     */
    private String birthday;

    /**
     * 入学时间yyyyMMdd
     */
    private String entranceTime;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 课程信息列表
     */
    private List<CourseInfoDTO> courses;
}
