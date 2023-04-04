package com.hdu.edu.creditcertificatesystem.controller;

import com.hdu.edu.creditcertificatesystem.pojo.dto.LoginInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author chenyb46701
 * @date 2023/3/13
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public BaseGenericsResponse<LoginInfoDTO> login(UserInfoRequest userInfoRequest) {
        final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
        return null;
    }

    @GetMapping("/count/byRole")
    public BaseGenericsResponse<List<Integer>> getCountsByRole(BaseRequest baseRequest) {
        return null;
    }
}
