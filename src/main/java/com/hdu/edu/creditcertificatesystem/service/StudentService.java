package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;

/**
 * 学生信息接口
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
@ContractLoader(value = ContractTypeEnum.STUDENT)
public interface StudentService {
    /**
     * 保存
     *
     * @param studentInfoRequest 学生请求
     */
    void save(StudentInfoRequest studentInfoRequest);
}
