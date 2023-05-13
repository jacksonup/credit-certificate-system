package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 班级信息DTO
 *
 * @author chenyb46701
 * @date 2023/5/13
 */
@Getter
@Setter
@ToString
public class ClassInfoDTO {
    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 班级姓名
     */
    private String className;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 班级内学生数量
     */
    private Integer count;
}
