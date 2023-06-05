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
    private String lessonId;

    private String studentId;

    private String lessonName;

    private String openCollege;

    private Integer regularScore;

    private Integer midTermScore;

    private Integer finalTermScore;

    private Integer finalScore;

    private Integer credit;

    private Integer point;

    private Integer creditPoint;

    private Integer term;

    private Boolean isRetake;

    private Boolean isReExam;
}
