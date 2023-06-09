package com.hdu.edu.creditcertificatesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.contract.TeacherContract;
import com.hdu.edu.creditcertificatesystem.entity.DepartmentInfo;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.mapper.DepartmentInfoMapper;
import com.hdu.edu.creditcertificatesystem.pojo.dto.DepartmentInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.DepartmentRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.DepartmentService;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 部门信息接口实现类
 *
 * @author chenyb46701
 * @date 2023/5/14
 */
@Slf4j
@Service
@ContractLoader(value = ContractTypeEnum.TEACHER)
public class DepartmentServiceImpl implements DepartmentService {
    @Setter(onMethod_ = @Autowired)
    private DepartmentInfoMapper departmentInfoMapper;

    private TeacherContract teacherContract;

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<String> create(DepartmentRequest departmentRequest) {
        if (ObjectUtils.isEmpty(departmentRequest) || ObjectUtils.isEmpty(departmentRequest.getDepartmentName())) {
            log.error("参数不能为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "参数不能为空");
        }

        // 判断班级名是否存在
        final List<DepartmentInfo> list = new LambdaQueryChainWrapper<>(departmentInfoMapper)
                .eq(DepartmentInfo::getDepartmentName, departmentRequest.getDepartmentName())
                .list();
        if (CollectionUtils.isNotEmpty(list) && list.size() > 0) {
            log.error("部门名已存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "部门名已存在");
        }
        DepartmentInfo departmentInfo = new DepartmentInfo();
        departmentInfo.setDepartmentName(departmentRequest.getDepartmentName());
        departmentInfoMapper.insert(departmentInfo);
        return BaseGenericsResponse.successBaseResp("创建成功");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<List<DepartmentInfoDTO>> getAllList(BaseRequest baseRequest) throws Exception {
        final List<DepartmentInfo> departmentInfoList = departmentInfoMapper.selectList(new QueryWrapper<>());
        List<DepartmentInfoDTO> departmentInfoDTOList = new ArrayList<>();

        // 查询部门内的人数
        final List<TeacherContract.TeacherInfo> teacherInfoList = teacherContract.getAll().send();
        HashMap<String, Integer> map = new HashMap<>();
        for (TeacherContract.TeacherInfo teacherInfo : teacherInfoList) {
            final String departmentName = teacherInfo.getDepartment();
            if (map.containsKey(departmentName)) {
                map.put(departmentName, map.get(departmentName) + 1);
            } else {
                map.put(departmentName, 1);
            }
        }
        if (CollectionUtils.isNotEmpty(departmentInfoList)) {
            for (DepartmentInfo departmentInfo : departmentInfoList) {
                final String departmentName = departmentInfo.getDepartmentName();
                DepartmentInfoDTO departmentInfoDTO = new DepartmentInfoDTO();
                departmentInfoDTO.setId(departmentInfo.getId());
                departmentInfoDTO.setDepartmentName(departmentName);
                departmentInfoDTO.setCount(map.getOrDefault(departmentName, 0));
                departmentInfoDTOList.add(departmentInfoDTO);
            }
        }
        return BaseGenericsResponse.successBaseResp(departmentInfoDTOList);
    }
}
