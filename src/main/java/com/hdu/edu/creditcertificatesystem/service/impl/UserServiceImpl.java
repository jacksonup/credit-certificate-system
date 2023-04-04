package com.hdu.edu.creditcertificatesystem.service.impl;

import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.mapstruct.UserInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import com.hdu.edu.creditcertificatesystem.property.ContractProperties;
import com.hdu.edu.creditcertificatesystem.service.UserService;
import com.hdu.edu.creditcertificatesystem.util.ValidatorUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.annotation.PostConstruct;

/**
 * 用户信息
 *
 * @author chenyb46701
 * @date 2023/3/31
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {
    @Setter(onMethod_ = @Autowired)
    private UserInfoConvert baseConvert;

    @Setter(onMethod_ = @Autowired)
    private ContractProperties contractProperties;

    private UserContract userContract;

    /**
     * 调用智能合约
     */
    @PostConstruct
    private void init() {
        // 监听本地链
        Web3j web3j = Web3j.build(new HttpService(contractProperties.getHttpService()));

        // 生成资格凭证
        Credentials credentials = Credentials.create(contractProperties.getCredentials());



    }

    /**
     * 获取DTO
     *
     * @param userInfoRequest 用户请求
     * @return
     */
    @Override
    public UserInfoDTO getDTO(UserInfoRequest userInfoRequest) {
        ValidatorUtils.validateEntity(userInfoRequest);
        userContract.getEntity(baseConvert.convert(userInfoRequest));

        return null;
    }
}
