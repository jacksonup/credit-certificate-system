package com.hdu.edu.creditcertificatesystem.mapstruct;

import com.hdu.edu.creditcertificatesystem.contract.InstitutionContract;
import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.pojo.dto.InstitutionDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.InstitutionRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 用户信息转换器
 *
 * @author chenyb46701
 * @date 2023/4/3
 */
public interface InstitutionConvert extends BaseConvert<InstitutionContract.InstitutionInfo, InstitutionRequest, InstitutionDTO, InstitutionContract.ExtraInfo>{
    /**
     * entity转DTO
     *
     * @param institutionInfo 机构信息
     * @param extraInfo 机构额外信息
     * @return 机构信息DTO
     */
    InstitutionDTO convertEx(InstitutionContract.InstitutionInfo institutionInfo,
                             InstitutionContract.ExtraInfo extraInfo);
}
