package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.pojo.dto.InstitutionDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.InstitutionRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;

import java.util.List;

/**
 * @author chenyb46701
 * @date 2023/5/1
 */
public interface InstitutionService {
    /**
     * 获取DTO
     *
     * @param institutionRequest 用户请求
     * @return 机构信息实体
     * @throws Exception 异常
     */
    InstitutionDTO getDTO(InstitutionRequest institutionRequest) throws Exception;

    /**
     * 分页获取List
     *
     * @param pageRequest 分页请求
     * @return 用户信息列表
     */
    List<InstitutionDTO> getListPage(PageRequest pageRequest) throws Exception;

    /**
     * 申请入驻
     *
     * @param institutionRequest 机构请求
     * @return 通用返回
     * @throws Exception 异常
     */
    BaseGenericsResponse<String> apply(InstitutionRequest institutionRequest) throws Exception;

    /**
     * 保存
     *
     * @param institutionRequest 机构信息请求类
     * @throws Exception 异常
     */
    void save(InstitutionRequest institutionRequest) throws Exception;

    /**
     * 删除
     *
     * @param institutionRequest 机构信息请求类
     * @throws Exception 异常
     */
    void delete(InstitutionRequest institutionRequest) throws Exception;

    /**
     * 根据审核类型获取列表
     *
     * @param pageRequest 分页请求
     * @return 机构信息DTO列表
     */
    List<InstitutionDTO> getListPageByStatus(PageRequest pageRequest) throws Exception;

    /**
     * 驳回
     *
     * @param institutionRequest 机构信息请求类
     * @return 通用返回信息
     */
    BaseGenericsResponse<String> reject(InstitutionRequest institutionRequest) throws Exception;

    /**
     * 通过
     *
     * @param institutionRequest 机构信息请求类
     * @return 通用返回信息
     */
    BaseGenericsResponse<String> pass(InstitutionRequest institutionRequest) throws Exception;
}
