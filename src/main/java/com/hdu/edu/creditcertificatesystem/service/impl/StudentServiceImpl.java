package com.hdu.edu.creditcertificatesystem.service.impl;

import com.hdu.edu.creditcertificatesystem.contract.StudentContract;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.mapstruct.StudentConvert;
import com.hdu.edu.creditcertificatesystem.mapstruct.UserInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.StudentInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;
import com.hdu.edu.creditcertificatesystem.service.StudentService;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学生信息接口实现类
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
@Slf4j
@Service("studentService")
@ContractLoader(value = ContractTypeEnum.STUDENT)
public class StudentServiceImpl implements StudentService {
    @Setter(onMethod_ = @Autowired)
    private StudentConvert baseConvert;
    private StudentContract studentContract;

    @Override
    public List<StudentInfoDTO> getList(StudentInfoRequest studentInfoRequest) {

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(StudentInfoRequest studentInfoRequest) {
        studentContract.save(
                baseConvert.convert(studentInfoRequest),
                baseConvert.convertEx(studentInfoRequest));
    }
}
