package com.hdu.edu.creditcertificatesystem.mapstruct.impl;

import com.hdu.edu.creditcertificatesystem.contract.TeacherContract;
import com.hdu.edu.creditcertificatesystem.mapstruct.TeacherInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.TeacherInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.TeacherInfoRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 教师信息转换实现类
 *
 * @author chenyb46701
 * @date 2023/5/23
 */
@Component
public class TeacherInfoConvertImpl implements TeacherInfoConvert {
    @Override
    public TeacherContract.TeacherInfo convert(TeacherInfoRequest request) {
//        return new TeacherContract.TeacherInfo(
//                request.getAccount(),
//                request.getName() == null ? "" : request.getName(),
//                request.getSexual() == null ? "" : request.getSexual(),
//                request.getIDPhoto() == null ? "" : request.getIDPhoto(),
//                request.getPoliticalOutlook() == null ? "" : request.getPoliticalOutlook(),
//                request.getEducationBg() == null ? "" : request.getEducationBg(),
//                request.getDepartment() == null ? "" : request.getDepartment(),
//                request.getBirthday(),
//                request.getDuration()
//        )
        return null;
    }

    @Override
    public ObjectUtils.Null convertEx(TeacherInfoRequest request) {
        return null;
    }

    @Override
    public TeacherInfoDTO convert(TeacherContract.TeacherInfo entity) {
        return null;
    }

    @Override
    public List<TeacherInfoDTO> list(List<TeacherContract.TeacherInfo> list) {
        return null;
    }
}
