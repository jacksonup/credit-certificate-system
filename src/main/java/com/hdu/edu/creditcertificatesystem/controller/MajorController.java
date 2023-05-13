package com.hdu.edu.creditcertificatesystem.controller;

import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.pojo.dto.MajorInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.MajorInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.MajorInfoService;
import com.hdu.edu.creditcertificatesystem.spring.CloudComponent;
import com.hdu.edu.creditcertificatesystem.spring.Permission;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 专业控制器
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Slf4j
@RestController
@RequestMapping("/major")
@ResponseBody
@CloudComponent
@CrossOrigin(origins = "*", maxAge = 3600)
public class MajorController {
    @Setter(onMethod_ = @Autowired)
    private MajorInfoService majorInfoService;

    /**
     * 按学院获取专业
     *
     * @param majorInfoRequest 基础请求
     * @return 专业信息
     */
    @GetMapping("/byFaculty")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.EDUCATIONAL_MANAGER})
    public BaseGenericsResponse<List<MajorInfoDTO>> getByFaculty(MajorInfoRequest majorInfoRequest) {
        return majorInfoService.getByFaculty(majorInfoRequest);
    }

    /**
     * 创建专业
     *
     * @param majorInfoRequest 专业信息请求
     * @return 创建成功/失败原因
     */
    @PostMapping("/create")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.EDUCATIONAL_MANAGER})
    public BaseGenericsResponse<String> create(MajorInfoRequest majorInfoRequest) {
        return majorInfoService.create(majorInfoRequest);
    }

    /**
     * 获取专业列表
     *
     * @param baseRequest 通用请求
     * @return 专业信息DTO列表
     */
    @GetMapping("/all")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.EDUCATIONAL_MANAGER})
    public BaseGenericsResponse<List<MajorInfoDTO>> getAllList(BaseRequest baseRequest) {
        return majorInfoService.getAllList(baseRequest);
    }
}
