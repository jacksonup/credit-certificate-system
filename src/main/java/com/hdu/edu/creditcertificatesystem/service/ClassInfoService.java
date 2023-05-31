package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.pojo.dto.ClassInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.ClassInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;

import java.util.List;

/**
 * 班级信息接口
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
public interface ClassInfoService {
    /**
     * 创建班级
     *
     * @param classInfoRequest 班级信息请求
     * @return 创建成功/失败原因
     */
    BaseGenericsResponse<String> create(ClassInfoRequest classInfoRequest);

    /**
     * 按专业获取班级
     *
     * @param classInfoRequest 班级信息请求
     * @return 班级信息DTO
     */
    BaseGenericsResponse<List<ClassInfoDTO>> getByMajor(ClassInfoRequest classInfoRequest);

    /**
     * 获取班级列表
     *
     * @param baseRequest 基础请求
     * @return 班级信息DTO列表
     */
    BaseGenericsResponse<List<ClassInfoDTO>> getAllList(BaseRequest baseRequest) throws Exception;
}
