package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 部门信息DTO
 *
 * @author chenyb46701
 * @date 2023/5/14
 */
@Getter
@Setter
@ToString
public class DepartmentInfoDTO {
    /**
     * 部门Id
     */
    private Integer id;

    /**
     * 部门名
     */
    private String departmentName;

    /**
     * 部门内人数
     */
    private Integer count;
}
