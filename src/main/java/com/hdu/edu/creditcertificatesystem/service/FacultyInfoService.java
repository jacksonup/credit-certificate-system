package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.pojo.dto.FacultyInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.FacultyInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;

import java.util.List;

/**
 * 机构信息接口
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
public interface FacultyInfoService {
    /**
     * 创建学院
     *
     * @param facultyInfoRequest 学院信息请求类
     * @return 通用返回
     */
    BaseGenericsResponse<String> create(FacultyInfoRequest facultyInfoRequest);

    /**
     * 获取所有学院列表
     *
     * @param baseRequest 基础请求
     * @return 学院信息DTO列表
     */
    BaseGenericsResponse<List<FacultyInfoDTO>> getAllList(BaseRequest baseRequest);
}
