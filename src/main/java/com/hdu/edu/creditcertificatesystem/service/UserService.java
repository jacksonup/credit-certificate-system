package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;

import java.util.List;

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
     * @return userInfoDTO
     */
    UserInfoDTO getDTO(UserInfoRequest userInfoRequest) throws Exception;

    /**
     * 获取List
     *
     * @param userInfoRequest 用户信息请求
     * @return List<UserInfoDTO> 用户信息列表
     */
    List<UserInfoDTO> getList(UserInfoRequest userInfoRequest);

    void test();
}
