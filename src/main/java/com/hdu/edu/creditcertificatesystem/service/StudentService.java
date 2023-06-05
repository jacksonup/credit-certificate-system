package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.pojo.dto.CourseInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.PageStudentInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.StudentInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;

import java.util.List;

/**
 * 学生信息接口
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
@ContractLoader(value = ContractTypeEnum.STUDENT)
public interface StudentService {
    List<StudentInfoDTO> getListPage(PageRequest pageRequest) throws Exception;

    /**
     * 保存
     *
     * @param studentInfoRequest 学生请求
     */
    void save(StudentInfoRequest studentInfoRequest) throws Exception;

    /**
     * 教务管理员分页搜索全部学生
     *
     * @param studentInfoRequest 学生信息请求
     * @return 学生信息DTO列表
     */
    BaseGenericsResponse<PageStudentInfoDTO> searchStuForAcaAdmin(StudentInfoRequest studentInfoRequest) throws Exception;

    /**
     * 机构分页管理搜索许可学生
     *
     * @param studentInfoRequest 学生信息请求
     * @return 学生信息DTO列表
     */
    BaseGenericsResponse<PageStudentInfoDTO> searchStuForInstitution(StudentInfoRequest studentInfoRequest) throws Exception;

    /**
     * 获取在校信息
     *
     * @param baseRequest 基础请求
     * @return 课程信息DTO列表
     */
    BaseGenericsResponse<List<CourseInfoDTO>> getSchoolInfo(BaseRequest baseRequest) throws Exception;
}
