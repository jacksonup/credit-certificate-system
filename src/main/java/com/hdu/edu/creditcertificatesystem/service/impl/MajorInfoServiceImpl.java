package com.hdu.edu.creditcertificatesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.entity.ClassInfo;
import com.hdu.edu.creditcertificatesystem.entity.FacultyInfo;
import com.hdu.edu.creditcertificatesystem.entity.MajorInfo;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.mapper.ClassInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapper.FacultyInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapper.MajorInfoMapper;
import com.hdu.edu.creditcertificatesystem.pojo.dto.MajorInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.MajorInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.MajorInfoService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenyb46701
 * @date 2023/5/12
 */
@Slf4j
@Service
public class MajorInfoServiceImpl implements MajorInfoService {
    @Setter(onMethod_ = @Autowired)
    private FacultyInfoMapper facultyInfoMapper;

    @Setter(onMethod_ = @Autowired)
    private MajorInfoMapper majorInfoMapper;

    @Setter(onMethod_ = @Autowired)
    private ClassInfoMapper classInfoMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<List<MajorInfoDTO>> getByFaculty(MajorInfoRequest majorInfoRequest) {
        if (ObjectUtils.isEmpty(majorInfoRequest) || ObjectUtils.isEmpty(majorInfoRequest.getFacultyId())) {
            log.error("参数不允许为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "参数不允许为空");
        }

        // 校验学院Id是否存在
        final FacultyInfo facultyInfo = facultyInfoMapper.selectById(majorInfoRequest.getFacultyId());
        if (ObjectUtils.isEmpty(facultyInfo)) {
            log.error("学院不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "学院不存在");
        }

        final List<MajorInfo> majorInfoList = new LambdaQueryChainWrapper<>(majorInfoMapper)
                .eq(MajorInfo::getFacultyId, majorInfoRequest.getFacultyId())
                .list();
        List<MajorInfoDTO> majorInfoDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(majorInfoList)) {
            for (MajorInfo majorInfo : majorInfoList) {
                MajorInfoDTO majorInfoDTO = new MajorInfoDTO();
                majorInfoDTO.setId(majorInfo.getId());
                majorInfoDTO.setMajorName(majorInfo.getMajorName());

                // 格式化时间
                majorInfoDTO.setCreateTime(majorInfo.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                majorInfoDTOList.add(majorInfoDTO);
            }
        }
        return BaseGenericsResponse.successBaseResp(majorInfoDTOList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<String> create(MajorInfoRequest majorInfoRequest) {
        if (ObjectUtils.isEmpty(majorInfoRequest) || StringUtils.isBlank(majorInfoRequest.getMajorName()) || ObjectUtils.isEmpty(majorInfoRequest.getFacultyId())) {
            log.error("参数不允许为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "参数不允许为空");
        }

        // 校验学院Id是否存在
        final FacultyInfo facultyInfo = facultyInfoMapper.selectById(majorInfoRequest.getFacultyId());
        if (ObjectUtils.isEmpty(facultyInfo)) {
            log.error("学院不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "学院不存在");
        }

        // 判断专业名是否重复
        final List<MajorInfo> list = new LambdaQueryChainWrapper<>(majorInfoMapper)
                .eq(MajorInfo::getMajorName, majorInfoRequest.getMajorName())
                .list();
        if (CollectionUtils.isNotEmpty(list) && list.size() > 0) {
            log.error("专业名已存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "专业名已存在");
        }

        MajorInfo majorInfo = new MajorInfo();
        majorInfo.setMajorName(majorInfoRequest.getMajorName());
        majorInfo.setFacultyId(majorInfoRequest.getFacultyId());
        majorInfoMapper.insert(majorInfo);
        return BaseGenericsResponse.successBaseResp("创建成功");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<List<MajorInfoDTO>> getAllList(BaseRequest baseRequest) {
        final List<MajorInfo> majorInfoList = majorInfoMapper.selectList(new QueryWrapper<>());
        List<MajorInfoDTO> majorInfoDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(majorInfoList)) {
            for (MajorInfo majorInfo : majorInfoList) {
                MajorInfoDTO majorInfoDTO = new MajorInfoDTO();
                // 查询绑定的班级
                final List<ClassInfo> classInfoList = new LambdaQueryChainWrapper<>(classInfoMapper)
                        .eq(ClassInfo::getMajorId, majorInfo.getId())
                        .list();
                if (CollectionUtils.isEmpty(classInfoList)) {
                    majorInfoDTO.setCount(0);
                } else {
                    majorInfoDTO.setCount(classInfoList.size());
                }
                majorInfoDTO.setId(majorInfo.getId());
                majorInfoDTO.setMajorName(majorInfo.getMajorName());
                majorInfoDTOList.add(majorInfoDTO);
            }
        }
        return BaseGenericsResponse.successBaseResp(majorInfoDTOList);
    }
}
