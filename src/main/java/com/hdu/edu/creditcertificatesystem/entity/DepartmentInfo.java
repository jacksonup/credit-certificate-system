package com.hdu.edu.creditcertificatesystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 部门信息
 *
 * @author chenyb46701
 * @date 2023/5/14
 */
@Getter
@Setter
@ToString
public class DepartmentInfo extends BaseEntity {
    /**
     * 部门名
     */
    private String departmentName;
}
