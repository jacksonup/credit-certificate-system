package com.hdu.edu.creditcertificatesystem.mapstruct;

import com.hdu.edu.creditcertificatesystem.contract.TeacherContract;
import com.hdu.edu.creditcertificatesystem.pojo.dto.TeacherInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.TeacherInfoRequest;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 教师信息转换器
 *
 * @author chenyb46701
 * @date 2023/5/23
 */
public interface TeacherInfoConvert extends BaseConvert<TeacherContract.TeacherInfo, TeacherInfoRequest, TeacherInfoDTO,  ObjectUtils.Null>{
}
