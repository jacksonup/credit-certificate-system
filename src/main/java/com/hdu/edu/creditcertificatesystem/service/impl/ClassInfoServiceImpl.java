package com.hdu.edu.creditcertificatesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.contract.StudentContract;
import com.hdu.edu.creditcertificatesystem.entity.ClassInfo;
import com.hdu.edu.creditcertificatesystem.entity.MajorInfo;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.mapper.ClassInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapper.MajorInfoMapper;
import com.hdu.edu.creditcertificatesystem.pojo.dto.ClassInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.ClassInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.ClassInfoService;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.tuples.generated.Tuple2;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 班级信息实现类
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Slf4j
@Service
@ContractLoader(value = {ContractTypeEnum.STUDENT})
public class ClassInfoServiceImpl implements ClassInfoService {
    @Setter(onMethod_ = @Autowired)
    private ClassInfoMapper classInfoMapper;

    @Setter(onMethod_ = @Autowired)
    private MajorInfoMapper majorInfoMapper;

    private StudentContract studentContract;

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<String> create(ClassInfoRequest classInfoRequest) {
        if (ObjectUtils.isEmpty(classInfoRequest) || ObjectUtils.isEmpty(classInfoRequest.getMajorId()) || ObjectUtils.isEmpty(classInfoRequest.getClassName())) {
            log.error("参数不能为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "参数不能为空");
        }

        // 校验专业Id是否存在
        final MajorInfo majorInfo = majorInfoMapper.selectById(classInfoRequest.getMajorId());
        if (ObjectUtils.isEmpty(majorInfo)) {
            log.error("专业不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "专业不存在");
        }

        // 判断班级名是否存在
        final List<ClassInfo> list = new LambdaQueryChainWrapper<>(classInfoMapper)
                .eq(ClassInfo::getClassName, classInfoRequest.getClassName())
                .list();
        if (CollectionUtils.isNotEmpty(list) && list.size() > 0) {
            log.error("班级名已存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "班级名已存在");
        }

        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassName(classInfoRequest.getClassName());
        classInfo.setMajorId(classInfoRequest.getMajorId());
        classInfoMapper.insert(classInfo);
        return BaseGenericsResponse.successBaseResp("创建成功");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<List<ClassInfoDTO>> getByMajor(ClassInfoRequest classInfoRequest) {
        if (ObjectUtils.isEmpty(classInfoRequest) || ObjectUtils.isEmpty(classInfoRequest.getMajorId())) {
            log.error("参数不允许为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "参数不允许为空");
        }

        // 校验专业Id是否存在
        final MajorInfo majorInfo = majorInfoMapper.selectById(classInfoRequest.getMajorId());
        if (ObjectUtils.isEmpty(majorInfo)) {
            log.error("专业不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "专业不存在");
        }

        List<ClassInfoDTO> classInfoDTOList = new ArrayList<>();
        final List<ClassInfo> classInfoList = new LambdaQueryChainWrapper<>(classInfoMapper)
                .eq(ClassInfo::getMajorId, classInfoRequest.getMajorId()).list();
        if (CollectionUtils.isNotEmpty(classInfoList)) {
            for (ClassInfo classInfo : classInfoList) {
                ClassInfoDTO classInfoDTO = new ClassInfoDTO();
                classInfoDTO.setId(classInfo.getId());
                classInfoDTO.setClassName(classInfo.getClassName());

                // 格式化时间
                classInfoDTO.setCreateTime(classInfo.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                classInfoDTOList.add(classInfoDTO);
            }
        }
        return BaseGenericsResponse.successBaseResp(classInfoDTOList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<List<ClassInfoDTO>> getAllList(BaseRequest baseRequest) throws Exception {
        List<ClassInfoDTO> classInfoDTOList = new ArrayList<>();
        final Tuple2<List<StudentContract.StudentInfo>, List<StudentContract.ExtraInfo>> listTuple2 = studentContract.getAll().send();

        // 判断班级是否为空
        if (null == listTuple2) {
            log.info("班级列表为空");
        } else {
            final List<StudentContract.StudentInfo> studentInfoList = listTuple2.getValue1();
            HashMap<String, Integer> map = new HashMap<>();
            for (StudentContract.StudentInfo studentInfo : studentInfoList) {
                final String grade = studentInfo.getGrade();
                if (map.containsKey(grade)) {
                    map.put(grade, map.get(grade) + 1);
                } else {
                    map.put(grade, 1);
                }
            }

            // 获取所有班级列表
            final List<ClassInfo> classInfos = classInfoMapper.selectList(new QueryWrapper<>());
            for (ClassInfo classInfo : classInfos) {
                final String className = classInfo.getClassName();
                ClassInfoDTO classInfoDTO = new ClassInfoDTO();
                classInfoDTO.setId(classInfo.getId());
                classInfoDTO.setClassName(className);
                classInfoDTO.setCount(map.getOrDefault(className, 0));

                // 格式化时间
                classInfoDTO.setCreateTime(classInfo.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                classInfoDTOList.add(classInfoDTO);
            }
        }

        return BaseGenericsResponse.successBaseResp(classInfoDTOList);
    }
}
