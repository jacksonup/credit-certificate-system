package com.hdu.edu.creditcertificatesystem.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.entity.ClassInfo;
import com.hdu.edu.creditcertificatesystem.entity.MajorInfo;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.mapper.ClassInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapper.MajorInfoMapper;
import com.hdu.edu.creditcertificatesystem.pojo.dto.ClassInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.ClassInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.ClassInfoService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 班级信息实现类
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Slf4j
@Service
public class ClassInfoServiceImpl implements ClassInfoService {
    @Setter(onMethod_ = @Autowired)
    private ClassInfoMapper classInfoMapper;

    @Setter(onMethod_ = @Autowired)
    private MajorInfoMapper majorInfoMapper;

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
    public BaseGenericsResponse<List<ClassInfoDTO>> getAllList(BaseRequest baseRequest) {

        return null;
    }
}
