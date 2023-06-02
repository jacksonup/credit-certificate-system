package com.hdu.edu.creditcertificatesystem.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.entity.ClassInfo;
import com.hdu.edu.creditcertificatesystem.entity.FacultyInfo;
import com.hdu.edu.creditcertificatesystem.entity.MajorInfo;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.mapper.ClassInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapper.FacultyInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapper.MajorInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapstruct.UserInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import com.hdu.edu.creditcertificatesystem.property.ContractProperties;
import com.hdu.edu.creditcertificatesystem.service.StudentService;
import com.hdu.edu.creditcertificatesystem.service.UserService;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import com.hdu.edu.creditcertificatesystem.util.ExcelUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 用户信息
 *
 * @author chenyb46701
 * @date 2023/3/31
 */
@Slf4j
@Service("userService")
@ContractLoader(value = ContractTypeEnum.USER)
public class UserServiceImpl implements UserService {
    @Setter(onMethod_ = @Autowired)
    private UserInfoConvert baseConvert;

    @Setter(onMethod_ = @Autowired)
    private ContractProperties contractProperties;

    @Setter(onMethod_ = @Autowired)
    private StudentService studentService;

    @Setter(onMethod_ = @Autowired)
    private FacultyInfoMapper facultyInfoMapper;

    @Setter(onMethod_ = @Autowired)
    private MajorInfoMapper majorInfoMapper;

    @Setter(onMethod_ = @Autowired)
    private ClassInfoMapper classInfoMapper;

    private UserContract userContract;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserInfoDTO getDTO(UserInfoRequest userInfoRequest) throws Exception {
        final UserInfoDTO userInfoDTO = baseConvert.convert(userContract.getEntity(baseConvert.convert(userInfoRequest)).send());
        if (ObjectUtils.isEmpty(userInfoDTO)) {
            log.info("用户不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "用户不存在");
        }
        return userInfoDTO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoDTO> getList(UserInfoRequest userInfoRequest) throws Exception {
        final List<UserContract.UserInfo> list = userContract.getAll().send();
        if (CollectionUtils.isEmpty(list)) {
            log.error("无数据");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "无数据");
        }
        List<UserInfoDTO> result = new ArrayList<>();
        for (UserContract.UserInfo userInfo : list) {
            result.add(baseConvert.convert(userInfo));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfoDTO> getListPage(PageRequest pageRequest) throws Exception {
        final List<UserContract.UserInfo> list = userContract.getListPage(
                new BigInteger(String.valueOf(pageRequest.getFrom())),
                new BigInteger("10")).send();
        if (CollectionUtils.isEmpty(list)) {
            log.error("无数据");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "无数据");
        }
        List<UserInfoDTO> result = new ArrayList<>();
        for (UserContract.UserInfo userInfo : list) {
            result.add(baseConvert.convert(userInfo));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getCountsByRole() throws Exception {
        final List<BigInteger> list = userContract.getCountsByRole().send();
        List<Integer> result = new ArrayList<>();
        for (BigInteger bigInteger : list) {
            result.add(bigInteger.intValue());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void importStudent(MultipartFile file) throws Exception {
        log.info("正在导入学生用户信息...");
        final List<Map<Integer, List<String>>> infoList = ExcelUtils.readExcel(file);
        List<UserInfoRequest> userInfoRequests = new ArrayList<>();
        List<StudentInfoRequest> studentInfoRequests = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (Map<Integer, List<String>> map : infoList) {
            for (Integer i : map.keySet()) {
                // 获取列的信息
                List<String> list = map.get(i);
                if (list.size() != 16) {
                    log.error("请输入内容，单元格不允许为空");
                    throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "请输入内容，单元格不允许为空");
                }
                if (set.contains(list.get(0))) {
                    log.error("");
                    continue;
                }
                set.add(list.get(0));

                // 校验院系是否存在
                String facultyName = list.get(8);
                final FacultyInfo facultyInfo = new LambdaQueryChainWrapper<>(facultyInfoMapper)
                        .eq(FacultyInfo::getFacultyName, facultyName)
                        .one();
                if (ObjectUtils.isEmpty(facultyInfo)) {
                    log.error("用户Id{}所在的院系{}不存在", list.get(0), facultyName);
                    throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, String.format(
                            "用户Id[%s]所在的院系[%s]不存在", list.get(0), facultyName
                    ));
                }

                // 专业是否存在
                String majorName = list.get(9);
                final MajorInfo majorInfo = new LambdaQueryChainWrapper<>(majorInfoMapper)
                        .eq(MajorInfo::getMajorName, majorName)
                        .eq(MajorInfo::getFacultyId, facultyInfo.getId())
                        .one();
                if (ObjectUtils.isEmpty(majorInfo)) {
                    log.error("用户Id{}所在的专业{}不存在", list.get(0), majorName);
                    throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, String.format(
                            "用户Id[%s]所在的专业[%s]不存在", list.get(0), majorName
                    ));
                }

                // 班级是否存在
                String className = list.get(10);
                final ClassInfo classInfo = new LambdaQueryChainWrapper<>(classInfoMapper)
                        .eq(ClassInfo::getClassName, className)
                        .eq(ClassInfo::getMajorId, majorInfo.getId())
                        .one();
                if (ObjectUtils.isEmpty(classInfo)) {
                    log.error("用户Id{}所在的班级{}不存在", list.get(0), className);
                    throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, String.format(
                            "用户Id[%s]所在的班级[%s]不存在", list.get(0), className
                    ));
                }

                // 生成用户信息
                UserInfoRequest userInfoRequest = new UserInfoRequest();
                userInfoRequest.setAccount(list.get(0));
                userInfoRequest.setPassword(list.get(0));
                userInfoRequest.setRole(RolePermissionEnum.STUDENT.getKey());
                userInfoRequest.setPhone(list.get(3));
                userInfoRequest.setEmail(list.get(4));

                // 生成学生信息
                StudentInfoRequest studentInfoRequest = new StudentInfoRequest();
                studentInfoRequest.setAccount(list.get(0));
                studentInfoRequest.setName(list.get(1));
                studentInfoRequest.setSexual(Integer.valueOf(list.get(2)));
                studentInfoRequest.setNativePlace(list.get(15));
                studentInfoRequest.setDepartment(list.get(8));
                studentInfoRequest.setMajor(list.get(9));
                studentInfoRequest.setGrade(list.get(10));
                studentInfoRequest.setEducationBg(list.get(11));
                studentInfoRequest.setPosition(list.get(12));
                studentInfoRequest.setPoliticalOutlook(list.get(13));
                studentInfoRequest.setIDPhoto(list.get(6));
                studentInfoRequest.setBirthPlace(list.get(14));
                studentInfoRequest.setBirthday(Integer.valueOf(list.get(5)));
                studentInfoRequest.setEntranceTime(Integer.valueOf(list.get(7)));

                userInfoRequests.add(userInfoRequest);
                studentInfoRequests.add(studentInfoRequest);
            }
        }

        for (UserInfoRequest userInfoRequest : userInfoRequests) {
            save(userInfoRequest);
        }

        for (StudentInfoRequest studentInfoRequest : studentInfoRequests) {
            studentService.save(studentInfoRequest);
        }
        log.info("导入学生成功");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(UserInfoRequest userInfoRequest) throws Exception {
        userContract.save(baseConvert.convert(userInfoRequest)).send();
    }
}
