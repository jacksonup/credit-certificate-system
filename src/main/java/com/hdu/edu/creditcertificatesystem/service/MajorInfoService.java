package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.pojo.dto.MajorInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.MajorInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;

import java.util.List;

/**
 * @author chenyb46701
 * @date 2023/5/12
 */
public interface MajorInfoService {
    /**
     * 按学院获取专业
     *
     * @param majorInfoRequest 基础请求
     * @return 专业信息
     */
    BaseGenericsResponse<List<MajorInfoDTO>> getByFaculty(MajorInfoRequest majorInfoRequest);

    /**
     * 创建专业
     *
     * @param majorInfoRequest 专业信息请求
     * @return 创建成功/失败原因
     */
    BaseGenericsResponse<String> create(MajorInfoRequest majorInfoRequest);

    /**
     * 获取专业列表
     *
     * @param baseRequest 通用请求
     * @return 专业信息DTO列表
     */
    BaseGenericsResponse<List<MajorInfoDTO>> getAllList(BaseRequest baseRequest);
}
