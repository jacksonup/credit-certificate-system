package com.hdu.edu.creditcertificatesystem.mapstruct.impl;

import com.hdu.edu.creditcertificatesystem.contract.InstitutionContract;
import com.hdu.edu.creditcertificatesystem.mapstruct.InstitutionConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.InstitutionDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.InstitutionRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 机构信息转换器实现类
 *
 * @author chenyb46701
 * @date 2023/5/4
 */
@Component
public class InstitutionConvertImpl implements InstitutionConvert {
    /**
     * {@inheritDoc}
     */
    @Override
    public InstitutionContract.InstitutionInfo convert(InstitutionRequest request) {
        return new InstitutionContract.InstitutionInfo(
                request.getId(),
                request.getFacultyId() == null ? "" : request.getFacultyId(),
                request.getMajorId() == null ? "" : request.getMajorId(),
                request.getAccount() == null ? "" : request.getAccount(),
                request.getPassword() == null ? "" : request.getPassword(),
                request.getInstitutionName() == null ? "" : request.getInstitutionName(),
                request.getInstitutionPhone() == null ? "" : request.getInstitutionPhone(),
                request.getInstitutionEmail() == null ? "" : request.getInstitutionEmail(),
                request.getInstitutionPlace() == null ? "" : request.getInstitutionPlace()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InstitutionContract.ExtraInfo convertEx(InstitutionRequest request) {
        return new InstitutionContract.ExtraInfo(
                request.getId(),
                BigInteger.valueOf(request.getStatus()),
                request.getAuthorCertificatePic() == null ? "" : request.getAuthorCertificatePic(),
                new BigInteger(String.valueOf(request.getCreateTime() == null ? 999 : request.getCreateTime())),
                new BigInteger(String.valueOf(request.getAuditTime() == null ? 999 : request.getAuditTime())),
                request.getMessage() == null ? "" : request.getMessage(),
                request.getReason() == null ? "" : request.getReason()
        );
    }

    @Override
    public InstitutionDTO convert(InstitutionContract.InstitutionInfo entity) {
        return null;
    }

    @Override
    public List<InstitutionDTO> list(List<InstitutionContract.InstitutionInfo> list) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InstitutionDTO convertEx(InstitutionContract.InstitutionInfo institutionInfo, InstitutionContract.ExtraInfo extraInfo) {
        InstitutionDTO institutionDTO = new InstitutionDTO();
        institutionDTO.setId(institutionInfo.getId());
        institutionDTO.setAccount(institutionInfo.getAccount());
        institutionDTO.setPassword(institutionInfo.getPassword());
        institutionDTO.setInstitutionName(institutionInfo.getInstitutionName());
        institutionDTO.setInstitutionPhone(institutionInfo.getInstitutionPhone());
        institutionDTO.setInstitutionEmail(institutionInfo.getInstitutionEmail());
        institutionDTO.setInstitutionPlace(institutionInfo.getInstitutionPlace());

        // 设置学院名列表
        if (StringUtils.isNotBlank(institutionInfo.getFacultyId())) {
            List<Long> list = new ArrayList<>();
            final String[] faculties = institutionInfo.getFacultyId().split(",");
            for (String s : faculties) {
                list.add(Long.valueOf(s));
            }
            institutionDTO.setFaculties(list);
        }

        // 设置专业名列表
        if (StringUtils.isNotBlank(institutionInfo.getMajorId())) {
            List<Long> list = new ArrayList<>();
            final String[] majors = institutionInfo.getMajorId().split(",");
            for (String s : majors) {
                list.add(Long.valueOf(s));
            }
            institutionDTO.setMajors(list);
        }

        // 构造扩展字段
        institutionDTO.setStatus(extraInfo.getStatus().intValue());
        institutionDTO.setAuthorCertificatePic(extraInfo.getAuthorCertificatePic());

        // 设置证明图片列表
        if (StringUtils.isNotBlank(extraInfo.getAuthorCertificatePic())) {
            List<String> list = new ArrayList<>();
            CollectionUtils.addAll(list, extraInfo.getAuthorCertificatePic().split(","));
            institutionDTO.setProves(list);
        }

        // 格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createTime = LocalDateTime.parse(extraInfo.getCreateTime().toString(), formatter);
        institutionDTO.setCreateTime(createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        if (StringUtils.isNotBlank(extraInfo.getAuditTime().toString()) && !extraInfo.getAuditTime().toString().equals("999")) {
            LocalDateTime auditTime = LocalDateTime.parse(extraInfo.getAuditTime().toString(), formatter);
            institutionDTO.setAuditTime(auditTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        } else {
            institutionDTO.setAuditTime("");
        }

        institutionDTO.setMessage(extraInfo.getMessage());
        institutionDTO.setReason(extraInfo.getReason());
        return institutionDTO;
    }
}
