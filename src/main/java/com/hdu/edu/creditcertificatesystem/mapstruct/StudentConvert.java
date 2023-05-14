package com.hdu.edu.creditcertificatesystem.mapstruct;

import com.hdu.edu.creditcertificatesystem.contract.StudentContract;
import com.hdu.edu.creditcertificatesystem.pojo.dto.StudentInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;

/**
 * 学生信息转换器
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
public interface StudentConvert extends BaseConvert<StudentContract.StudentInfo, StudentInfoRequest, StudentInfoDTO, StudentContract.ExtraInfo>{
}
