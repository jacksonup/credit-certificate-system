package com.hdu.edu.creditcertificatesystem.mapstruct.impl;

import com.hdu.edu.creditcertificatesystem.contract.TeacherContract;
import com.hdu.edu.creditcertificatesystem.mapstruct.TeacherInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.TeacherInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.TeacherInfoRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        return new TeacherContract.TeacherInfo(
                request.getAccount(),
                request.getName(),
                new BigInteger(String.valueOf(request.getSexual())),
                request.getIDPhoto() == null ? "" : request.getIDPhoto(),
                request.getPosition(),
                request.getDuty(),
                request.getPoliticalOutlook(),
                request.getEducationalBg(),
                request.getDepartment(),
                request.getDuration(),
                request.getBirthday()
        );
    }

    @Override
    public ObjectUtils.Null convertEx(TeacherInfoRequest request) {
        return null;
    }

    @Override
    public TeacherInfoDTO convert(TeacherContract.TeacherInfo entity) {
        TeacherInfoDTO teacherInfoDTO = new TeacherInfoDTO();
        teacherInfoDTO.setName(entity.getTeacherName());
        teacherInfoDTO.setAccount(entity.getAccount());
        teacherInfoDTO.setSexual(entity.getSexual().intValue() == 0 ? "女" : "男");

        // 解析时间
        teacherInfoDTO.setBirthday(entity.getBirthday());
        teacherInfoDTO.setDuration(entity.getDuration());
        teacherInfoDTO.setPosition(entity.getPosition());
        teacherInfoDTO.setDepartment(entity.getDepartment());
        teacherInfoDTO.setEducationalBg(entity.getEducationalBg());
        teacherInfoDTO.setDuty(entity.getDuty());
        teacherInfoDTO.setPoliticalOutlook(entity.getPoliticalOutlook());
        return teacherInfoDTO;
    }

    @Override
    public List<TeacherInfoDTO> list(List<TeacherContract.TeacherInfo> list) {
        return null;
    }
}
