package com.hdu.edu.creditcertificatesystem.mapstruct;

import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;

/**
 * 用户信息转换器
 *
 * @author chenyb46701
 * @date 2023/4/3
 */
public interface UserInfoConvert extends BaseConvert<UserContract.UserInfo, UserInfoRequest, UserInfoDTO>{
}
