package com.hdu.edu.creditcertificatesystem.service.impl;

import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.mapstruct.UserInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import com.hdu.edu.creditcertificatesystem.property.ContractProperties;
import com.hdu.edu.creditcertificatesystem.service.UserService;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import com.hdu.edu.creditcertificatesystem.util.ValidatorUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 用户信息
 *
 * @author chenyb46701
 * @date 2023/3/31
 */
@Slf4j
@Service("userService")
@ContractLoader(value = ContractTypeEnum.USER)
public class UserServiceImpl implements UserService {
    @Setter(onMethod_ = @Autowired)
    private UserInfoConvert baseConvert;

    @Setter(onMethod_ = @Autowired)
    private ContractProperties contractProperties;

    private UserContract userContract;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfoDTO getDTO(UserInfoRequest userInfoRequest) throws Exception {
        ValidatorUtils.validateEntity(userInfoRequest);
        return baseConvert.convert(userContract.getEntity(baseConvert.convert(userInfoRequest)).send());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoDTO> getList(UserInfoRequest userInfoRequest) {

        return null;
    }

    @Override
    public void test() {
        System.out.println(userContract.getContractAddress());
        log.info("进入test");
    }
}
