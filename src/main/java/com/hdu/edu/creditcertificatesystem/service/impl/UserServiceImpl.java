package com.hdu.edu.creditcertificatesystem.service.impl;

import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.mapstruct.UserInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import com.hdu.edu.creditcertificatesystem.property.ContractProperties;
import com.hdu.edu.creditcertificatesystem.service.UserService;
import com.hdu.edu.creditcertificatesystem.spring.CloudComponent;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import com.hdu.edu.creditcertificatesystem.util.ValidatorUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
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
@CloudComponent
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoDTO> getListPage(PageRequest pageRequest) throws Exception {
        final List<UserContract.UserInfo> list = userContract.getListPage(
                new BigInteger(String.valueOf(pageRequest.getFrom())),
                new BigInteger("10")).send();
        if (CollectionUtils.isEmpty(list)) {
            log.error("无数据");
            throw new BaseException(ErrorCodeConstant.NO_EXIST_CODE, "无数据");
        }
        List<UserInfoDTO> result = new ArrayList<>();
        for (UserContract.UserInfo userInfo : list) {
            result.add(baseConvert.convert(userInfo));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getCountsByRole() throws Exception {
        final List<BigInteger> list = userContract.getCountsByRole().send();
        List<Integer> result = new ArrayList<>();
        for (BigInteger bigInteger : list) {
            result.add(bigInteger.intValue());
        }
        return result;
    }

    @Override
    public void test() {
        System.out.println(userContract.getContractAddress());
        log.info("进入test");
    }
}
