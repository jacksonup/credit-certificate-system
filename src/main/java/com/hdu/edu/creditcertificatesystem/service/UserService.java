package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;

/**
 * 用户接口
 *
 * @author chenyb46701
 * @date 2023/3/31
 */
public interface UserService {
    /**
     * 获取DTO
     *
     * @param userInfoRequest 用户请求
     * @return
     */
    UserInfoDTO getDTO(UserInfoRequest userInfoRequest);
}
