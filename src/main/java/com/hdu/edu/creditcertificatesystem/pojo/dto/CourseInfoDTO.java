package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

/**
 * 课程信息DTO
 *
 * @author chenyb46701
 * @date 2023/5/28
 */
@Getter
@Setter
@ToString
public class CourseInfoDTO {
    public String lessonId;

    public String studentId;

    public String lessonName;

    public String openCollege;

    public Integer regularScore;

    public Integer midTermScore;

    public Integer finalTermScore;

    public Integer finalScore;

    public Integer credit;

    public Integer point;

    public Integer creditPoint;

    public Integer term;
}
