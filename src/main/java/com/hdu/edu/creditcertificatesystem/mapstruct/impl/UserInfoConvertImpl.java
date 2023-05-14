package com.hdu.edu.creditcertificatesystem.mapstruct.impl;

import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.mapstruct.UserInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
                new BigInteger(String.valueOf(request.getRole() == null ? 999 : request.getRole())),
                new BigInteger(String.valueOf(request.getCreateTime() == null ? 999 : request.getCreateTime())),
                request.getPhone() == null ? "" : request.getPhone(),
                request.getEmail() == null ? "" : request.getEmail()
        );
    }

    @Override
    public ObjectUtils.Null convertEx(UserInfoRequest request) {
        return null;
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
        userInfoDTO.setPassword(entity.getPassword());
        userInfoDTO.setRole(entity.getRole().intValue());
        userInfoDTO.setPhone(entity.getPhone());
        userInfoDTO.setEmail(entity.getEmail());

        // 格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime dateTime = LocalDateTime.parse(entity.getCreateTime().toString(), formatter);
        userInfoDTO.setCreateTime(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return userInfoDTO;
    }

    @Override
    public List<UserInfoDTO> list(List<UserContract.UserInfo> list) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfoRequest one(UserInfoDTO userInfoDTO) {
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setAccount(userInfoDTO.getAccount());
        userInfoRequest.setPassword(userInfoDTO.getPassword());
        userInfoRequest.setPhone(userInfoRequest.getPhone());
        userInfoRequest.setEmail(userInfoRequest.getEmail());
        userInfoRequest.setRole(userInfoDTO.getRole());
        userInfoRequest.setCreateTime(userInfoDTO.getCreateTime());
        return userInfoRequest;
    }
}
