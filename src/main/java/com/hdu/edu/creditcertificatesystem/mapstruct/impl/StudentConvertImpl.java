package com.hdu.edu.creditcertificatesystem.mapstruct.impl;

import com.hdu.edu.creditcertificatesystem.contract.StudentContract;
import com.hdu.edu.creditcertificatesystem.mapstruct.StudentConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.StudentInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * 学生信息转换器实现嘞
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
@Component
public class StudentConvertImpl implements StudentConvert {
    @Override
    public StudentContract.StudentInfo convert(StudentInfoRequest request) {
        return new StudentContract.StudentInfo(
                request.getAccount(),
                request.getName(),
                new BigInteger(String.valueOf(request.getSexual())),
                request.getNativePlace(),
                request.getDepartment(),
                request.getMajor(),
                request.getGrade(),
                request.getEducationBg()
        );
    }

    @Override
    public StudentInfoDTO convert(StudentContract.StudentInfo entity) {
        return null;
    }

    @Override
    public List<StudentInfoDTO> list(List<StudentContract.StudentInfo> list) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentContract.ExtraInfo convertEx(StudentInfoRequest studentInfoRequest) {
        return new StudentContract.ExtraInfo(
                studentInfoRequest.getAccount(),
                studentInfoRequest.getPosition(),
                studentInfoRequest.getPoliticalOutlook(),
                studentInfoRequest.getIDPhoto(),
                studentInfoRequest.getBirthPlace(),
                new BigInteger(String.valueOf(studentInfoRequest.getBirthday())),
                new BigInteger(String.valueOf(studentInfoRequest.getEntranceTime()))
        );
    }
}
