package com.hdu.edu.creditcertificatesystem.service.impl;

import com.hdu.edu.creditcertificatesystem.contract.StudentContract;
import com.hdu.edu.creditcertificatesystem.mapstruct.StudentConvert;
import com.hdu.edu.creditcertificatesystem.mapstruct.UserInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;
import com.hdu.edu.creditcertificatesystem.service.StudentService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生信息接口实现类
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Setter(onMethod_ = @Autowired)
    private StudentConvert baseConvert;
    private StudentContract studentContract;

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
