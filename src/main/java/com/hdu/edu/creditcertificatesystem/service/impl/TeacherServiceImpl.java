package com.hdu.edu.creditcertificatesystem.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.contract.TeacherContract;
import com.hdu.edu.creditcertificatesystem.contract.UserContract;
import com.hdu.edu.creditcertificatesystem.entity.DepartmentInfo;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.mapper.DepartmentInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapstruct.TeacherInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.TeacherInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.UserInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.TeacherInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.TeacherService;
import com.hdu.edu.creditcertificatesystem.service.UserService;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import com.hdu.edu.creditcertificatesystem.util.ExcelUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.tx.exceptions.ContractCallException;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 教师信息接口实现类
 *
 * @author chenyb46701
 * @date 2023/5/13
 */
@Slf4j
@Service("teacherService")
@ContractLoader(value = {ContractTypeEnum.TEACHER, ContractTypeEnum.USER})
public class TeacherServiceImpl implements TeacherService {
    @Setter(onMethod_ = @Autowired)
    private TeacherInfoConvert baseConvert;

    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @Setter(onMethod_ = @Autowired)
    private DepartmentInfoMapper departmentInfoMapper;

    private TeacherContract teacherContract;

    private UserContract userContract;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(TeacherInfoRequest teacherInfoRequest) throws Exception {
        teacherContract.save(baseConvert.convert(teacherInfoRequest)).send();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void importTeacher(MultipartFile file) throws Exception {
        log.info("正在导入学生用户信息...");
        final List<Map<Integer, List<String>>> infoList = ExcelUtils.readExcel(file);
        List<TeacherInfoRequest> teacherInfoRequests = new ArrayList<>();
        List<UserInfoRequest> userInfoRequests = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        for (Map<Integer, List<String>> map : infoList) {
            for (Integer i : map.keySet()) {
                // 获取列的信息
                List<String> list = map.get(i);
                if (list.size() != 13) {
                    log.error("请输入内容，单元格不允许为空");
                    throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "请输入内容，单元格不允许为空");
                }
                if (set.contains(list.get(0))) {
                    log.error("");
                    continue;
                }
                set.add(list.get(0));

                // 判断部门是否存在于系统中
                final DepartmentInfo departmentInfo = new LambdaQueryChainWrapper<>(departmentInfoMapper)
                        .eq(DepartmentInfo::getDepartmentName, list.get(11))
                        .one();
                if (ObjectUtils.isEmpty(departmentInfo)) {
                    log.error("部门不存在");
                    throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "部门不存在");
                }

                // 生成用户信息
                UserInfoRequest userInfoRequest = new UserInfoRequest();
                userInfoRequest.setAccount(list.get(0));
                userInfoRequest.setPassword(list.get(0));
                userInfoRequest.setRole(RolePermissionEnum.TEACHER.getKey());
                userInfoRequest.setPhone(list.get(3));
                userInfoRequest.setEmail(list.get(4));
                userInfoRequest.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
                userInfoRequests.add(userInfoRequest);

                // 生成教师信息
                TeacherInfoRequest teacherInfoRequest = new TeacherInfoRequest();
                teacherInfoRequest.setAccount(list.get(0));
                teacherInfoRequest.setName(list.get(1));
                teacherInfoRequest.setSexual(list.get(2).equals("男") ? 1 : 0);
                teacherInfoRequest.setBirthday(list.get(5));
                teacherInfoRequest.setIDPhoto(list.get(6));
                teacherInfoRequest.setDuration(list.get(7));
                teacherInfoRequest.setDuty(list.get(8));
                teacherInfoRequest.setPoliticalOutlook(list.get(9));
                teacherInfoRequest.setPosition(list.get(10));
                teacherInfoRequest.setDepartment(list.get(11));
                teacherInfoRequest.setEducationalBg(list.get(12));
                teacherInfoRequests.add(teacherInfoRequest);
            }
        }

        for (UserInfoRequest userInfoRequest : userInfoRequests) {
            userService.save(userInfoRequest);
        }

        for (TeacherInfoRequest teacherInfoRequest : teacherInfoRequests) {
            save(teacherInfoRequest);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<List<TeacherInfoDTO>> getTeacherInfoListByRole(PageRequest pageRequest) throws Exception {
        try {
            // 判断角色Id是否正确
            if (!Objects.equals(pageRequest.getRole(), RolePermissionEnum.EDUCATIONAL_MANAGER.getKey()) &&
                    !Objects.equals(pageRequest.getRole(), RolePermissionEnum.INSTITUTE_MANAGER.getKey()) &&
                    !Objects.equals(pageRequest.getRole(), RolePermissionEnum.TEACHER.getKey())) {
                log.error("获取的教师角色只可能是1、2、4");
                throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "获取的教师角色只可能是1、2、4");
            }

            final List<TeacherContract.TeacherInfo> teacherInfos = teacherContract.getListPageByRole(
                    new BigInteger(String.valueOf(pageRequest.getRole())),
                    new BigInteger(String.valueOf(pageRequest.getFrom())),
                    new BigInteger(String.valueOf(pageRequest.getFrom() + 10)),
                    userContract.getContractAddress()).send();

            List<TeacherInfoDTO> teacherInfoDTOS = new ArrayList<>();
            for (TeacherContract.TeacherInfo teacherInfo : teacherInfos) {
                TeacherInfoDTO teacherInfoDTO = baseConvert.convert(teacherInfo);

                // 查询手机号和邮箱
                UserInfoRequest userInfoRequest = new UserInfoRequest();
                userInfoRequest.setAccount(teacherInfo.getAccount());
                final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
                teacherInfoDTO.setPhone(userInfoDTO.getPhone());
                teacherInfoDTO.setEmail(userInfoDTO.getEmail());
                teacherInfoDTOS.add(teacherInfoDTO);
            }
            return BaseGenericsResponse.successBaseResp(teacherInfoDTOS);
        } catch (ContractCallException e) {
            log.error("数据为空");
            return BaseGenericsResponse.successBaseResp(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<List<TeacherInfoDTO>> getTeacherInfoListBySectorId(PageRequest pageRequest) throws Exception {
        try {
            final DepartmentInfo departmentInfo = departmentInfoMapper.selectById(pageRequest.getSectorId());
            if (ObjectUtils.isEmpty(departmentInfo)) {
                log.error("部门不存在");
                throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "部门不存在");
            }

            final List<TeacherContract.TeacherInfo> teacherInfos = teacherContract.getListPageBySector(
                    departmentInfo.getDepartmentName(),
                    new BigInteger(String.valueOf(pageRequest.getFrom())),
                    new BigInteger(String.valueOf(pageRequest.getFrom() + 10))).send();

            List<TeacherInfoDTO> teacherInfoDTOS = new ArrayList<>();
            for (TeacherContract.TeacherInfo teacherInfo : teacherInfos) {
                TeacherInfoDTO teacherInfoDTO = baseConvert.convert(teacherInfo);

                // 查询手机号和邮箱
                UserInfoRequest userInfoRequest = new UserInfoRequest();
                userInfoRequest.setAccount(teacherInfo.getAccount());
                final UserInfoDTO userInfoDTO = userService.getDTO(userInfoRequest);
                teacherInfoDTO.setPhone(userInfoDTO.getPhone());
                teacherInfoDTO.setEmail(userInfoDTO.getEmail());
                teacherInfoDTOS.add(teacherInfoDTO);
            }
            return BaseGenericsResponse.successBaseResp(teacherInfoDTOS);
        } catch (ContractCallException e) {
            log.error("数据为空");
            return BaseGenericsResponse.successBaseResp(null);
        }
    }
}
