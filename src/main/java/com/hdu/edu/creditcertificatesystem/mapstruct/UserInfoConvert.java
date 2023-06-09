package com.hdu.edu.creditcertificatesystem.mapstruct;

import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 用户信息转换器
 *
 * @author chenyb46701
 * @date 2023/4/3
 */
public interface UserInfoConvert extends BaseConvert<UserContract.UserInfo, UserInfoRequest, UserInfoDTO, ObjectUtils.Null>{
    /**
     * DTO转request
     *
     * @param userInfoDTO 用户信息DTO
     * @return 用户信息请求
     */
    UserInfoRequest one(UserInfoDTO userInfoDTO);
}
