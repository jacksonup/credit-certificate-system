package com.hdu.edu.creditcertificatesystem.controller;

import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.pojo.dto.InstitutionDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.LoginInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.TokenDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.InstitutionRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.UserService;
import com.hdu.edu.creditcertificatesystem.spring.Permission;
import com.hdu.edu.creditcertificatesystem.util.JwtUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author chenyb46701
 * @date 2023/3/13
 */
@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    /**
     * 登录
     *
     * @param userInfoRequest 请求
     * @return 登录信息DTO
     */
    @PostMapping("/login")
    public BaseGenericsResponse<LoginInfoDTO> login(UserInfoRequest userInfoRequest) throws Exception {
        final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
        if (ObjectUtils.isEmpty(userInfoDTO)) {
            log.info("用户不存在");
            return BaseGenericsResponse.failureBaseResp(ErrorCodeConstant.CUSTOM_CODE, "用户不存在");
        }

        if (!userInfoDTO.getPassword().equals(userInfoRequest.getPassword())) {
            log.info("密码错误");
            return BaseGenericsResponse.failureBaseResp(ErrorCodeConstant.CUSTOM_CODE, "密码错误");
        }

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccount(userInfoDTO.getAccount());
        tokenDTO.setRole(userInfoDTO.getRole());

        LoginInfoDTO loginInfoDTO = new LoginInfoDTO();
        loginInfoDTO.setToken(JwtUtils.createToken(tokenDTO));
        loginInfoDTO.setRole(userInfoDTO.getRole());

        return BaseGenericsResponse.successBaseResp(loginInfoDTO);
    }

    /**
     * 按角色获取用户数量
     *
     * @param baseRequest 基本请求
     * @return 用户数量列表
     */
    @GetMapping("/count/byRole")
    @Permission(role = RolePermissionEnum.ADMIN)
    public BaseGenericsResponse<List<Integer>> countByRole(BaseRequest baseRequest) throws Exception {
        return BaseGenericsResponse.successBaseResp(userService.getCountsByRole());
    }

    /**
     * 分页获取所有用户
     *
     * @param pageRequest 分页请求
     * @return 用户信息DTO列表
     */
    @GetMapping("/user/all")
    @Permission(role = {RolePermissionEnum.ADMIN})
    public BaseGenericsResponse<List<UserInfoDTO>> getAllUser(PageRequest pageRequest) throws Exception {
        return BaseGenericsResponse.successBaseResp(userService.getListPage(pageRequest));
    }

    /**
     * 分页获取机构
     *
     * @param pageRequest 分页请求
     * @return 机构信息实体类
     */
    @GetMapping("/user/institution/all")
    public BaseGenericsResponse<InstitutionDTO> getAllInstitution(PageRequest pageRequest) {
        return null;
    }

    /**
     * 申请入驻
     *
     * @param institutionRequest 机构信息请求
     * @return 审核通过信息
     */
    @PostMapping("institution/apply")
    public BaseGenericsResponse<String> apply(InstitutionRequest institutionRequest) {

        return null;
    }
}
