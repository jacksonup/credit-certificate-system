package com.hdu.edu.creditcertificatesystem.controller;

import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.mapstruct.UserInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.*;
import com.hdu.edu.creditcertificatesystem.pojo.request.*;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.*;
import com.hdu.edu.creditcertificatesystem.spring.CloudComponent;
import com.hdu.edu.creditcertificatesystem.spring.Permission;
import com.hdu.edu.creditcertificatesystem.util.JwtUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户控制器
 *
 * @author chenyb46701
 * @date 2023/3/13
 */
@Slf4j
@RestController
@RequestMapping("/user")
@ResponseBody
@CloudComponent
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @Setter(onMethod_ = @Autowired)
    private TeacherService teacherService;

    @Setter(onMethod_ = @Autowired)
    private InstitutionService institutionService;

    @Setter(onMethod_ = @Autowired)
    private StudentService studentService;

    @Setter(onMethod_ = @Autowired)
    private CourseInfoService courseInfoService;

    @Setter(onMethod_ = @Autowired)
    private UserInfoConvert baseConvert;

    /**
     * 登录
     *
     * @param userInfoRequest 请求
     * @return 登录信息DTO
     */
    @PostMapping("/login")
    public BaseGenericsResponse<LoginInfoDTO> login(UserInfoRequest userInfoRequest) throws Exception {
        final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
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
    @GetMapping("/all")
    @Permission(role = {RolePermissionEnum.ADMIN})
    public BaseGenericsResponse<List<UserInfoDTO>> getAllUser(PageRequest pageRequest) throws Exception {
        return BaseGenericsResponse.successBaseResp(userService.getListPage(pageRequest));
    }

    /**
     * 导入学生
     *
     * @param baseRequest 基础请求
     * @param file 学生文件
     * @return 导入信息
     */
    @PostMapping("/student/import")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.EDUCATIONAL_MANAGER})
    public BaseGenericsResponse<String> importStudent(BaseRequest baseRequest, @RequestParam("file") MultipartFile file) throws Exception {
        userService.importStudent(file);
        return BaseGenericsResponse.successBaseResp("导入成功");
    }

    /**
     * 分页获取学生
     *
     * @param pageRequest 分页请求
     * @return 学生信息列表
     */
    @GetMapping("/student/all")
    public BaseGenericsResponse<List<StudentInfoDTO>> getAllStudent(PageRequest pageRequest) throws Exception {
        return BaseGenericsResponse.successBaseResp(studentService.getListPage(pageRequest));
    }

    /**
     * 获取在校信息
     *
     * @param baseRequest 基础请求
     * @return
     * @throws Exception
     */
    @GetMapping("/student/schoolInfo")
    @Permission(role = RolePermissionEnum.STUDENT)
    public BaseGenericsResponse<List<CourseInfoDTO>> getSchoolInfo(BaseRequest baseRequest) throws Exception {
        return studentService.getSchoolInfo(baseRequest);
    }

    /**
     * 导入教师
     *
     * @param baseRequest 通用请求
     * @param file 教师文件
     * @return 导入信息
     */
    @PostMapping("/teacher/import")
    @Permission(role = {RolePermissionEnum.ADMIN})
    public BaseGenericsResponse<String> importTeacher(BaseRequest baseRequest, @RequestParam("file") MultipartFile file) throws Exception {
        teacherService.importTeacher(file);
        return BaseGenericsResponse.successBaseResp("导入成功");
    }

    /**
     * 分页按角色获取教师
     *
     * @param pageRequest 分页请求
     * @return 教师信息列表
     */
    @PostMapping("/teacher/byRole")
    @Permission(role = {RolePermissionEnum.ADMIN})
    public BaseGenericsResponse<List<TeacherInfoDTO>> getTeacherInfoListByRole(PageRequest pageRequest) throws Exception {
        return teacherService.getTeacherInfoListByRole(pageRequest);
    }

    /**
     * 分页按部门Id获取教师
     *
     * @param pageRequest 分页请求
     * @return 教师信息列表
     */
    @PostMapping("/teacher/bySector")
    @Permission(role = {RolePermissionEnum.ADMIN})
    public BaseGenericsResponse<List<TeacherInfoDTO>> getTeacherInfoListBySectorId(PageRequest pageRequest) throws Exception {
        return teacherService.getTeacherInfoListBySectorId(pageRequest);
    }

    /**
     * 按id获取已审核表详情
     *
     * @param institutionRequest 机构信息请求类
     * @return 机构信息DTO
     */
    @GetMapping("/auditor/audited")
    @Permission(role = RolePermissionEnum.INSTITUTE_MANAGER)
    public BaseGenericsResponse<InstitutionDTO> getAuditedById(InstitutionRequest institutionRequest) throws Exception {
        return BaseGenericsResponse.successBaseResp(institutionService.getDTO(institutionRequest));
    }

    /**
     * 按id获取待审核表详情
     *
     * @param institutionRequest 机构信息请求类
     * @return 机构信息DTO
     */
    @GetMapping("/auditor/auditing")
    @Permission(role = RolePermissionEnum.INSTITUTE_MANAGER)
    public BaseGenericsResponse<InstitutionDTO> getAuditingById(InstitutionRequest institutionRequest) throws Exception {
        return BaseGenericsResponse.successBaseResp(institutionService.getDTO(institutionRequest));
    }

    /**
     * 分页按类型获取审核列表
     *
     * @param pageRequest 分页请求
     * @return 机构分页DTO
     */
    @GetMapping("/auditor/audits")
    @Permission(role = RolePermissionEnum.INSTITUTE_MANAGER)
    public BaseGenericsResponse<InstitutionPageDTO> getAuditsPage(PageRequest pageRequest) throws Exception {
        List<InstitutionDTO> listPage;
        if (pageRequest.getStatus() == 2) {
            listPage = institutionService.getListPage(pageRequest);
        } else {
            listPage = institutionService.getListPageByStatus(pageRequest);
        }

        final InstitutionPageDTO institutionPageDTO = new InstitutionPageDTO();
        if (CollectionUtils.isEmpty(listPage)) {
            institutionPageDTO.setCount(0);
        } else {
            institutionPageDTO.setCount(listPage.size());
            institutionPageDTO.setList(listPage);
        }
        return BaseGenericsResponse.successBaseResp(institutionPageDTO);
    }

    /**
     * 审核驳回
     *
     * @param institutionRequest 机构信息请求类
     * @return 驳回信息
     */
    @PostMapping("/auditor/no")
    @Permission(role = RolePermissionEnum.INSTITUTE_MANAGER)
    public BaseGenericsResponse<String> reject(InstitutionRequest institutionRequest) throws Exception {
        return institutionService.reject(institutionRequest);
    }

    /**
     * 审核通过
     *
     * @param institutionRequest 机构信息请求类
     * @return 通过信息
     */
    @PostMapping("/auditor/ok")
    @Permission(role = RolePermissionEnum.INSTITUTE_MANAGER)
    public BaseGenericsResponse<String> pass(InstitutionRequest institutionRequest) throws Exception {
        return institutionService.pass(institutionRequest);
    }

    /**
     * 取消机构审核员
     *
     * @return 取消成功/失败原因
     */
    @PostMapping("/auditor/remove")
    @Permission(role = RolePermissionEnum.ADMIN)
    public BaseGenericsResponse<String> remove(UserInfoRequest userInfoRequest) throws Exception {
        final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
        if (!Objects.equals(userInfoDTO.getRole(), RolePermissionEnum.INSTITUTE_MANAGER.getKey())) {
            log.error("用户信息必须为机构管理员角色");
            return BaseGenericsResponse.failureBaseResp(ErrorCodeConstant.CUSTOM_CODE, "用户信息必须为机构管理员角色");
        }
        userInfoDTO.setRole(RolePermissionEnum.TEACHER.getKey());
        userService.save(baseConvert.one(userInfoDTO));
        return BaseGenericsResponse.successBaseResp("取消成功");
    }

    /**
     * 设为机构审核员
     *
     * @param userInfoRequest 用户信息请求类
     * @return 设置成功/失败原因
     */
    @PostMapping("/auditor/set")
    @Permission(role = RolePermissionEnum.ADMIN)
    public BaseGenericsResponse<String> set(UserInfoRequest userInfoRequest) throws Exception {
        final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
        if (!Objects.equals(userInfoDTO.getRole(), RolePermissionEnum.TEACHER.getKey())) {
            log.error("用户信息必须为普通教师角色");
            return BaseGenericsResponse.failureBaseResp(ErrorCodeConstant.CUSTOM_CODE, "用户信息必须为普通教师角色");
        }
        userInfoDTO.setRole(RolePermissionEnum.INSTITUTE_MANAGER.getKey());
        userService.save(baseConvert.one(userInfoDTO));
        return BaseGenericsResponse.successBaseResp("设置成功");
    }

    /**
     * 取消教务管理员
     *
     * @param userInfoRequest 用户信息请求类
     * @return 设置成功/失败原因
     */
    @PostMapping("/acaAdmin/remove")
    @Permission(role = RolePermissionEnum.ADMIN)
    public BaseGenericsResponse<String> removeAcaAdmin(UserInfoRequest userInfoRequest) throws Exception {
        final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
        if (!Objects.equals(userInfoDTO.getRole(), RolePermissionEnum.EDUCATIONAL_MANAGER.getKey())) {
            log.error("用户信息必须为教务管理员角色");
            return BaseGenericsResponse.failureBaseResp(ErrorCodeConstant.CUSTOM_CODE, "用户信息必须为教务管理员角色");
        }
        userInfoDTO.setRole(RolePermissionEnum.TEACHER.getKey());
        userService.save(baseConvert.one(userInfoDTO));
        return BaseGenericsResponse.successBaseResp("取消成功");
    }

    /**
     * 设为教务管理员
     *
     * @param userInfoRequest 用户信息请求类
     * @return 设置成功/失败原因
     */
    @PostMapping("/acaAdmin/set")
    @Permission(role = RolePermissionEnum.ADMIN)
    public BaseGenericsResponse<String> setAcaAdmin(UserInfoRequest userInfoRequest) throws Exception {
        final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
        if (!Objects.equals(userInfoDTO.getRole(), RolePermissionEnum.TEACHER.getKey())) {
            log.error("用户信息必须为普通教师角色");
            return BaseGenericsResponse.failureBaseResp(ErrorCodeConstant.CUSTOM_CODE, "用户信息必须为普通教师角色");
        }
        userInfoDTO.setRole(RolePermissionEnum.EDUCATIONAL_MANAGER.getKey());
        userService.save(baseConvert.one(userInfoDTO));
        return BaseGenericsResponse.successBaseResp("设置成功");
    }

    /**
     * 教务管理员分页搜索全部学生
     *
     * @param studentInfoRequest 学生信息请求
     * @return 学生信息DTO列表
     */
    @GetMapping("/acaAdmin/searchStu")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.EDUCATIONAL_MANAGER})
    public BaseGenericsResponse<PageStudentInfoDTO> acaAdminSearchStu(StudentInfoRequest studentInfoRequest) throws Exception {
        return studentService.searchStuForAcaAdmin(studentInfoRequest);
    }

    /**
     * 添加课程记录
     *
     * @param baseRequest 基础请求
     * @param file 文件
     * @return 是否成功
     */
    @PostMapping("/acaAdmin/addCourse")
    @Permission(role = {RolePermissionEnum.ADMIN, RolePermissionEnum.EDUCATIONAL_MANAGER})
    public BaseGenericsResponse<String> importCourse(BaseRequest baseRequest, @RequestParam("file") MultipartFile file) {
        courseInfoService.importCourse(file);
        return BaseGenericsResponse.successBaseResp("导入成功");
    }

    /**
     * 获取所有系统管理员
     *
     * @param baseRequest 基础请求
     * @return 管理员用户信息列表
     */
    @GetMapping("/admin/all")
    @Permission(role = RolePermissionEnum.ADMIN)
    public BaseGenericsResponse<List<UserInfoDTO>> getAllAdmin(BaseRequest baseRequest) throws Exception {
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setToken(baseRequest.getToken());
        final List<UserInfoDTO> list = userService.getList(userInfoRequest);
        List<UserInfoDTO> result = new ArrayList<>();
        for (UserInfoDTO userInfoDTO : list) {
            if (Objects.equals(userInfoDTO.getRole(), RolePermissionEnum.ADMIN.getKey())) {
                result.add(userInfoDTO);
            }
        }
        return BaseGenericsResponse.successBaseResp(result);
    }
}
