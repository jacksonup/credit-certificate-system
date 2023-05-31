package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.pojo.dto.DepartmentInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.DepartmentRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;

import java.util.List;

/**
 * 部门接口
 *
 * @author chenyb46701
 * @date 2023/5/14
 */
public interface DepartmentService {
    /**
     * 创建部门
     *
     * @param departmentRequest 部门请求
     * @return 创建成功/失败原因
     */
    BaseGenericsResponse<String> create(DepartmentRequest departmentRequest);

    /**
     * 获取部门列表
     *
     * @param baseRequest 通用请求
     * @return 部门DTO列表
     */
    BaseGenericsResponse<List<DepartmentInfoDTO>> getAllList(BaseRequest baseRequest) throws Exception;
}
