package com.hdu.edu.creditcertificatesystem.controller;

import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.pojo.dto.ClassInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.ClassInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.ClassInfoService;
import com.hdu.edu.creditcertificatesystem.spring.CloudComponent;
import com.hdu.edu.creditcertificatesystem.spring.Permission;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级控制器
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Slf4j
@RestController
@RequestMapping("/class")
@ResponseBody
@CloudComponent
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClassController {
    @Setter(onMethod_ = @Autowired)
    private ClassInfoService classInfoService;

    /**
     * 创建班级
     *
     * @param classInfoRequest 班级信息请求
     * @return 创建成功/失败原因
     */
    @PostMapping("/create")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.EDUCATIONAL_MANAGER})
    public BaseGenericsResponse<String> create(ClassInfoRequest classInfoRequest) {
        return classInfoService.create(classInfoRequest);
    }

    /**
     * 按专业获取班级
     *
     * @param classInfoRequest 班级信息请求
     * @return 班级信息DTO
     */
    @GetMapping("/byMajor")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.EDUCATIONAL_MANAGER})
    public BaseGenericsResponse<List<ClassInfoDTO>> getByMajor(ClassInfoRequest classInfoRequest) {
        return classInfoService.getByMajor(classInfoRequest);
    }

    /**
     * 获取班级列表
     *
     * @param baseRequest 基础请求
     * @return 班级信息DTO列表
     */
    @GetMapping("/all")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.EDUCATIONAL_MANAGER})
    public BaseGenericsResponse<List<ClassInfoDTO>> getAllList(BaseRequest baseRequest) throws Exception {
        return classInfoService.getAllList(baseRequest);
    }
}
