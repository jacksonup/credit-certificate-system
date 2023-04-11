package com.hdu.edu.creditcertificatesystem.mapstruct.impl;

import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.mapstruct.UserInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * 用户信息转换类实现
 *
 * @author chenyb46701
 * @date 2023/4/3
 */
@Component
public class UserInfoConvertImpl implements UserInfoConvert {
    /**
     * Request转Entity
     *
     * @param request 业务请求
     * @return Entity
     */
    @Override
    public UserContract.UserInfo convert(UserInfoRequest request) {
        return new UserContract.UserInfo(
                request.getAccount(),
                request.getPassword(),
                new BigInteger(String.valueOf(request.getRole())),
                new BigInteger(String.valueOf(request.getCreateTime())),
                request.getPhone(),
                request.getEmail()
        );
    }

    /**
     * Entity转DTO
     *
     * @param entity Entity
     * @return DTO
     */
    @Override
    public UserInfoDTO convert(UserContract.UserInfo entity) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setAccount(entity.getAccount());
        userInfoDTO.setRole(entity.getRole().intValue());
        userInfoDTO.setPhone(entity.getPhone());
        userInfoDTO.setEmail(entity.getEmail());
        return userInfoDTO;
    }
}
