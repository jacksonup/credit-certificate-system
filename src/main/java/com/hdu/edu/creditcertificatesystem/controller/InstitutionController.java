package com.hdu.edu.creditcertificatesystem.controller;

import com.hdu.edu.creditcertificatesystem.enums.AuditTypeEnum;
import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.pojo.dto.InstitutionDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.PageInstitutionDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.PageStudentInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.InstitutionRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.InstitutionService;
import com.hdu.edu.creditcertificatesystem.service.StudentService;
import com.hdu.edu.creditcertificatesystem.spring.CloudComponent;
import com.hdu.edu.creditcertificatesystem.spring.Permission;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author chenyb46701
 * @date 2023/5/31
 */
@Slf4j
@RestController
@RequestMapping("/user/institution")
@ResponseBody
@CloudComponent
@CrossOrigin(origins = "*", maxAge = 3600)
public class InstitutionController {

    @Setter(onMethod_ = @Autowired)
    private InstitutionService institutionService;

    @Setter(onMethod_ = @Autowired)
    private StudentService studentService;

    /**
     * 分页获取机构
     *
     * @param pageRequest 分页请求
     * @return 机构信息实体类
     */
    @GetMapping("/all")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.INSTITUTE_MANAGER})
    public BaseGenericsResponse<PageInstitutionDTO> getAllInstitution(PageRequest pageRequest) throws Exception {
        final List<InstitutionDTO> listPage = institutionService.getListPage(pageRequest);

        // 过滤未审批的数据
        listPage.removeIf(institutionDTO -> Objects.equals(institutionDTO.getStatus(), AuditTypeEnum.WAIT_AUDIT.getKey()));

        PageInstitutionDTO pageInstitutionDTO = new PageInstitutionDTO();
        if (CollectionUtils.isEmpty(listPage)) {
            pageInstitutionDTO.setCount(0);
        } else {
            pageInstitutionDTO.setInstitutions(listPage);
            pageInstitutionDTO.setCount(listPage.size());
        }
        return BaseGenericsResponse.successBaseResp(pageInstitutionDTO);
    }

    /**
     * 申请入驻
     *
     * @param institutionRequest 机构信息请求
     * @return 审核通过信息
     */
    @PostMapping("/apply")
    public BaseGenericsResponse<String> apply(InstitutionRequest institutionRequest) throws Exception {
        return institutionService.apply(institutionRequest);
    }

    /**
     * 机构分页管理搜索许可学生
     *
     * @param studentInfoRequest 学生信息请求
     * @return 学生信息DTO列表
     */
    @GetMapping("/searchStu")
    @Permission(role = RolePermissionEnum.INSTITUTE)
    public BaseGenericsResponse<PageStudentInfoDTO> institutionSearchStu(StudentInfoRequest studentInfoRequest) throws Exception {
        return studentService.searchStuForInstitution(studentInfoRequest);
    }
}
