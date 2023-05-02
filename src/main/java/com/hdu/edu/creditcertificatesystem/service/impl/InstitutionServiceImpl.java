package com.hdu.edu.creditcertificatesystem.service.impl;

import com.hdu.edu.creditcertificatesystem.contract.InstitutionContract;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.pojo.dto.InstitutionDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.InstitutionRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.service.InstitutionService;
import com.hdu.edu.creditcertificatesystem.spring.CloudComponent;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构信息接口实现
 *
 * @author chenyb46701
 * @date 2023/5/1
 */
@Slf4j
@Service("institutionService")
@ContractLoader(value = ContractTypeEnum.INSTITUTION)
@CloudComponent
public class InstitutionServiceImpl implements InstitutionService {
    private InstitutionContract institutionContract;

    @Override
    public InstitutionDTO getDTO(InstitutionRequest institutionRequest) throws Exception {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<InstitutionDTO> getListPage(PageRequest pageRequest) throws Exception {
        return null;
    }
}
