package com.hdu.edu.creditcertificatesystem.controller;

import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.pojo.dto.DepartmentInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.DepartmentRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.DepartmentService;
import com.hdu.edu.creditcertificatesystem.spring.CloudComponent;
import com.hdu.edu.creditcertificatesystem.spring.Permission;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门信息控制器
 *
 * @author chenyb46701
 * @date 2023/5/14
 */
@Slf4j
@RestController
@RequestMapping("/sector")
@ResponseBody
@CloudComponent
@CrossOrigin(origins = "*", maxAge = 3600)
public class DepartmentController {
    @Setter(onMethod_ = @Autowired)
    private DepartmentService departmentService;

    /**
     * 创建部门
     *
     * @param departmentRequest 部门请求
     * @return 创建成功/失败原因
     */
    @PostMapping("/create")
    @Permission(role = RolePermissionEnum.ADMIN)
    public BaseGenericsResponse<String> create(DepartmentRequest departmentRequest) {
        return departmentService.create(departmentRequest);
    }

    /**
     * 获取部门列表
     *
     * @param baseRequest 通用请求
     * @return 部门DTO列表
     */
    @GetMapping("/all")
    @Permission(role = RolePermissionEnum.ADMIN)
    public BaseGenericsResponse<List<DepartmentInfoDTO>> getAllList(BaseRequest baseRequest) throws Exception {
        return departmentService.getAllList(baseRequest);
    }
}
