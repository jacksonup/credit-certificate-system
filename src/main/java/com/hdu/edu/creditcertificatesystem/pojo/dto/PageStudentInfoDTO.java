package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 分页搜索学生信息DTO
 *
 * @author chenyb46701
 * @date 2023/5/28
 */
@Getter
@Setter
@ToString
public class PageStudentInfoDTO {
    /**
     * 学生信息列表
     */
    private List<StudentInfoDTO> students;

    /**
     * 搜索出的学生总数
     */
    private Integer count;
}
