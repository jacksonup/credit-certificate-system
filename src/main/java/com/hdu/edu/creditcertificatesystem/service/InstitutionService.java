package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.pojo.dto.InstitutionDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.InstitutionRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;

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
}
