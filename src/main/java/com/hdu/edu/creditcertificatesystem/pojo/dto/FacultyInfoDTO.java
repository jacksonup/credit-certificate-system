package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 学院信息DTO
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Getter
@Setter
@ToString
public class FacultyInfoDTO {
    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 学院名
     */
    private String facultyName;

    /**
     * 学院内专业数量
     */
    private Integer count;
}
