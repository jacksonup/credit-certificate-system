package com.hdu.edu.creditcertificatesystem.pojo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

/**
 * 学生信息请求
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
@Getter
@Setter
@ToString
public class StudentInfoRequest extends BaseRequest {
    /**
     * 学号
     */
    public String account;

    /**
     * 姓名
     */
    public String name;

    /**
     * 性别
     */
    public Integer sexual;

    /**
     * 籍贯
     */
    public String nativePlace;

    /**
     * 院系
     */
    public String department;

    /**
     * 专业
     */
    public String major;

    /**
     * 班级
     */
    public String grade;

    /**
     * 学历
     */
    public String educationBg;

    /**
     * 担任职务
     */
    public String position;

    /**
     * 政治面貌
     */
    public String politicalOutlook;

    /**
     * 证件照路径
     */
    public String IDPhoto;

    /**
     * 生源地
     */
    public String birthPlace;

    /**
     * 出生日期yyyyMMdd
     */
    public Integer birthday;

    /**
     * 入学时间yyyyMMdd
     */
    public Integer entranceTime;
}
