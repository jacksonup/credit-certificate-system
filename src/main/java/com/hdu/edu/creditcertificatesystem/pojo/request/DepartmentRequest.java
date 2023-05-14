package com.hdu.edu.creditcertificatesystem.pojo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 部门请求
 *
 * @author chenyb46701
 * @date 2023/5/14
 */
@Getter
@Setter
@ToString
public class DepartmentRequest extends BaseRequest{
    /**
     * 部门名
     */
    private String departmentName;
}
