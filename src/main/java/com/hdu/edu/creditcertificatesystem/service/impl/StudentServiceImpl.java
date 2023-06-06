package com.hdu.edu.creditcertificatesystem.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.contract.InstitutionContract;
import com.hdu.edu.creditcertificatesystem.contract.StudentContract;
import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.entity.FacultyInfo;
import com.hdu.edu.creditcertificatesystem.entity.MajorInfo;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.mapper.FacultyInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapper.MajorInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapstruct.StudentInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.*;
import com.hdu.edu.creditcertificatesystem.pojo.request.*;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.CourseInfoService;
import com.hdu.edu.creditcertificatesystem.service.InstitutionService;
import com.hdu.edu.creditcertificatesystem.service.StudentService;
import com.hdu.edu.creditcertificatesystem.service.UserService;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import com.hdu.edu.creditcertificatesystem.util.JwtUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生信息接口实现类
 *
 * @author chenyb46701
 * @date 2023/5/2
 */
@Slf4j
@Service("studentService")
@ContractLoader(value = ContractTypeEnum.STUDENT)
public class StudentServiceImpl implements StudentService {
    @Setter(onMethod_ = @Autowired)
    private StudentInfoConvert baseConvert;

    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @Setter(onMethod_ = @Autowired)
    private CourseInfoService courseInfoService;

    @Setter(onMethod_ = @Autowired)
    private InstitutionService institutionService;

    @Setter(onMethod_ = @Autowired)
    private FacultyInfoMapper facultyInfoMapper;

    @Setter(onMethod_ = @Autowired)
    private MajorInfoMapper majorInfoMapper;

    private StudentContract studentContract;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StudentInfoDTO> getListPage(PageRequest pageRequest) throws Exception {
        if (ObjectUtils.isEmpty(pageRequest) || ObjectUtils.isEmpty(pageRequest.getFrom())) {
            log.error("参数不允许为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "参数不允许为空");
        }

        final Tuple2<List<StudentContract.StudentInfo>, List<StudentContract.ExtraInfo>> listTuple2 = studentContract.getListPage(
                new BigInteger(String.valueOf(pageRequest.getFrom())),
                new BigInteger(String.valueOf(pageRequest.getFrom() + 9))).send();

        List<StudentInfoDTO> result = new ArrayList<>();
        List<StudentContract.StudentInfo> studentInfoList;
        List<StudentContract.ExtraInfo> extraInfoList;
        if (null != listTuple2) {
            studentInfoList = listTuple2.getValue1();
            extraInfoList = listTuple2.getValue2();

            if (CollectionUtils.isNotEmpty(studentInfoList) && CollectionUtils.isNotEmpty(extraInfoList)) {
                if (studentInfoList.size() != extraInfoList.size()) {
                    log.error("用户信息和扩展信息不匹配");
                    throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "用户信息和扩展信息不匹配");
                }

                // entity转化为dto
                for (int i = 0; i < studentInfoList.size(); i++) {
                    StudentInfoDTO studentInfoDTO = baseConvert.convertEx(studentInfoList.get(i), extraInfoList.get(i));

                    // 查询学生手机号和邮箱
                    UserInfoRequest userInfoRequest = new UserInfoRequest();
                    userInfoRequest.setAccount(studentInfoDTO.getAccount());
                    final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
                    studentInfoDTO.setPhone(userInfoDTO.getPhone());
                    studentInfoDTO.setEmail(userInfoDTO.getEmail());

                    // 根据学生Id获取课程信息
                    studentInfoDTO.setCourses(courseInfoService.getListByStudentId(studentInfoList.get(i).getAccount()));
                    result.add(studentInfoDTO);
                }
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(StudentInfoRequest studentInfoRequest) throws Exception {
        studentContract.save(
                baseConvert.convert(studentInfoRequest),
                baseConvert.convertEx(studentInfoRequest)).send();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<PageStudentInfoDTO> searchStuForAcaAdmin(StudentInfoRequest studentInfoRequest) throws Exception {
        final Tuple2<List<StudentContract.StudentInfo>, List<StudentContract.ExtraInfo>> listTuple2 = studentContract.searchStu(
                studentInfoRequest.getDepartment(),
                studentInfoRequest.getMajor(),
                studentInfoRequest.getGrade(),
                studentInfoRequest.getName()).send();

        if (null != listTuple2) {
            PageStudentInfoDTO pageStudentInfoDTO = new PageStudentInfoDTO();
            List<StudentInfoDTO> students = new ArrayList<>();
            final List<StudentContract.StudentInfo> studentInfoList = listTuple2.getValue1();
            final List<StudentContract.ExtraInfo> extraInfoList = listTuple2.getValue2();

            for (int i = 0; i < studentInfoList.size(); i++) {
                // 过滤空值
                if (StringUtils.isBlank(studentInfoList.get(i).getAccount()) || StringUtils.isBlank(extraInfoList.get(i).getAccount())) {
                    log.info("数据为空");
                    break;
                }

                StudentInfoDTO studentInfoDTO = baseConvert.convertEx(studentInfoList.get(i), extraInfoList.get(i));

                // 查询学生手机号和邮箱
                UserInfoRequest userInfoRequest = new UserInfoRequest();
                userInfoRequest.setAccount(studentInfoDTO.getAccount());
                final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
                studentInfoDTO.setPhone(userInfoDTO.getPhone());
                studentInfoDTO.setEmail(userInfoDTO.getEmail());

                // 根据学生Id获取课程信息
                studentInfoDTO.setCourses(courseInfoService.getListByStudentId(studentInfoList.get(i).getAccount()));
                students.add(studentInfoDTO);
            }
            if (CollectionUtils.isEmpty(students)) {
                pageStudentInfoDTO.setCount(0);
            } else {
                pageStudentInfoDTO.setStudents(students);
                pageStudentInfoDTO.setCount(students.size());
            }
            return BaseGenericsResponse.successBaseResp(pageStudentInfoDTO);
        }
        return BaseGenericsResponse.failureBaseResp(ErrorCodeConstant.CUSTOM_CODE, "获取信息异常");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<PageStudentInfoDTO> searchStuForInstitution(StudentInfoRequest studentInfoRequest) throws Exception {
        // 获取账号
        final String institutionAccount = JwtUtils.getTokenInfo(studentInfoRequest.getToken()).getClaim("account").asString();

        // 获取机构的权限
        InstitutionRequest institutionRequest = new InstitutionRequest();

        // 截取末尾10000获得
        final String id = institutionAccount.substring(0, institutionAccount.length() - 5);
        institutionRequest.setId(id);
        final InstitutionDTO institutionDTO = institutionService.getDTO(institutionRequest);
        final List<Long> faculties = institutionDTO.getFaculties();
        final List<Long> majors = institutionDTO.getMajors();

        // 判断是否有该学院权限
        if (StringUtils.isBlank(studentInfoRequest.getDepartment())) {
            log.error("参数学院名为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "参数学院名为空");
        }

        final FacultyInfo facultyInfo = new LambdaQueryChainWrapper<>(facultyInfoMapper)
                .eq(FacultyInfo::getFacultyName, studentInfoRequest.getDepartment())
                .one();
        if (ObjectUtils.isEmpty(facultyInfo)) {
            log.error("学院不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "学院不存在");
        }
        final Integer facultyInfoId = facultyInfo.getId();
        boolean flag = false;
        for (Long l : faculties) {
            if (l.intValue() == facultyInfoId) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            log.error("无权限查看该学院信息");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "无权限查看该学院信息");
        }

        // 判断是否有该专业权限
        if (StringUtils.isBlank(studentInfoRequest.getMajor())) {
            log.error("参数专业名为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "参数专业名为空");
        }

        final MajorInfo majorInfo = new LambdaQueryChainWrapper<>(majorInfoMapper)
                .eq(MajorInfo::getMajorName, studentInfoRequest.getMajor())
                .one();
        final Integer majorInfoId = majorInfo.getId();
        flag = false;
        for (Long l : majors) {
            if (l.intValue() == majorInfoId) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            log.error("无权限查看该专业信息");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "无权限查看该专业信息");
        }

        return searchStuForAcaAdmin(studentInfoRequest);
    }

    /**
     * 获取在校信息
     *
     * @param baseRequest 基础请求
     * @return 课程信息列表
     */
    @Override
    public BaseGenericsResponse<List<CourseInfoDTO>> getSchoolInfo(BaseRequest baseRequest) throws Exception {
        final String account = JwtUtils.getTokenInfo(baseRequest.getToken()).getClaim("account").asString();
        if (StringUtils.isBlank(account)) {
            log.error("Token中账号信息为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "Token中账号信息为空");
        }
        final List<CourseInfoDTO> courseInfoDTOS = courseInfoService.getListByStudentId(account);
        if (CollectionUtils.isEmpty(courseInfoDTOS)) {
            log.error("课程信息为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "课程信息为空");
        }
        return BaseGenericsResponse.successBaseResp(courseInfoDTOS);
    }
}
