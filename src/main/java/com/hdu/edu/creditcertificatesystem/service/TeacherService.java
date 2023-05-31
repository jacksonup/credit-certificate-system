package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.pojo.dto.TeacherInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.TeacherInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 教师接口
 *
 * @author chenyb46701
 * @date 2023/5/13
 */
public interface TeacherService {
    /**
     * 保存
     *
     * @param teacherInfoRequest 教师信息请求
     */
    void save(TeacherInfoRequest teacherInfoRequest) throws Exception;

    /**
     * 导入教师
     *
     * @param file 教师文件
     */
    void importTeacher(MultipartFile file) throws Exception;

    /**
     * 分页按角色获取教师
     *
     * @param pageRequest 分页请求
     * @return 教师信息DTO列表
     */
    BaseGenericsResponse<List<TeacherInfoDTO>> getTeacherInfoListByRole(PageRequest pageRequest) throws Exception;

    /**
     * 分页按部门获取教师
     *
     * @param pageRequest 分页请求
     * @return 教师信息DTO列表
     */
    BaseGenericsResponse<List<TeacherInfoDTO>> getTeacherInfoListBySectorId(PageRequest pageRequest) throws Exception;
}
