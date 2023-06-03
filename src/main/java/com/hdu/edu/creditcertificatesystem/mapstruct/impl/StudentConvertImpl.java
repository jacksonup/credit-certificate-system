package com.hdu.edu.creditcertificatesystem.mapstruct.impl;

import com.hdu.edu.creditcertificatesystem.contract.StudentContract;
import com.hdu.edu.creditcertificatesystem.mapstruct.StudentInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.StudentInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 学生信息转换器实现类
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
@Component
public class StudentConvertImpl implements StudentInfoConvert {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public StudentInfoDTO convertEx(StudentContract.StudentInfo studentInfo, StudentContract.ExtraInfo extraInfo) {
        StudentInfoDTO studentInfoDTO = new StudentInfoDTO();
        studentInfoDTO.setAccount(studentInfo.getAccount());
        studentInfoDTO.setName(studentInfo.getName());
        studentInfoDTO.setSexual(studentInfo.getSexual().intValue() == 0 ? "女" : "男");
        studentInfoDTO.setNativePlace(studentInfo.getNativePlace());
        studentInfoDTO.setDepartment(studentInfo.getDepartment());
        studentInfoDTO.setMajor(studentInfo.getMajor());
        studentInfoDTO.setGrade(studentInfo.getGrade());
        studentInfoDTO.setEducationBg(studentInfo.getEducationBg());
        studentInfoDTO.setBirthPlace(studentInfo.getNativePlace());
        studentInfoDTO.setPosition(extraInfo.getPosition());
        studentInfoDTO.setNativePlace(studentInfo.getNativePlace());
        studentInfoDTO.setPoliticalOutlook(extraInfo.getPoliticalOutlook());

        // 解析时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(String.valueOf(extraInfo.getBirthday()), formatter);
        studentInfoDTO.setBirthday(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        localDate = LocalDate.parse(String.valueOf(extraInfo.getEntranceTime()), formatter);
        studentInfoDTO.setEntranceTime(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return studentInfoDTO;
    }
}
