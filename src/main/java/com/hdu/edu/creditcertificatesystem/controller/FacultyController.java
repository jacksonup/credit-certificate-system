package com.hdu.edu.creditcertificatesystem.controller;

import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.pojo.dto.FacultyInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.FacultyInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.FacultyInfoService;
import com.hdu.edu.creditcertificatesystem.spring.CloudComponent;
import com.hdu.edu.creditcertificatesystem.spring.Permission;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学院控制器
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Slf4j
@RestController
@RequestMapping("/faculty")
@ResponseBody
@CloudComponent
@CrossOrigin(origins = "*", maxAge = 3600)
public class FacultyController {
    @Setter(onMethod_ = @Autowired)
    private FacultyInfoService facultyInfoService;

    /**
     * 创建学院
     *
     * @param facultyInfoRequest 学院信息请求类
     * @return 通用返回
     */
    @PostMapping("/create")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.EDUCATIONAL_MANAGER})
    public BaseGenericsResponse<String> create(FacultyInfoRequest facultyInfoRequest) {
        return facultyInfoService.create(facultyInfoRequest);
    }

    /**
     * 获取学院列表
     *
     * @param baseRequest 基础信息请求类
     * @return 学院信息列表
     */
    @GetMapping("/all")
    public BaseGenericsResponse<List<FacultyInfoDTO>> getAllList(BaseRequest baseRequest) {
        return facultyInfoService.getAllList(baseRequest);
    }

}
