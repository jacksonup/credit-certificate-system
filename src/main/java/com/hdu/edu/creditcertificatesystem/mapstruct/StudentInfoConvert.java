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
public interface StudentInfoConvert extends BaseConvert<StudentContract.StudentInfo, StudentInfoRequest, StudentInfoDTO, StudentContract.ExtraInfo>{
    /**
     * entity转DTO
     *
     * @param studentInfo 机构信息
     * @param extraInfo 机构额外信息
     * @return 机构信息DTO
     */
    StudentInfoDTO convertEx(StudentContract.StudentInfo studentInfo,
                             StudentContract.ExtraInfo extraInfo);
}
