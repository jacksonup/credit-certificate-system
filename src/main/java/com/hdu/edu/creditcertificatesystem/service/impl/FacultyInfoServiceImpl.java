package com.hdu.edu.creditcertificatesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.entity.FacultyInfo;
import com.hdu.edu.creditcertificatesystem.entity.MajorInfo;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.mapper.FacultyInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapper.MajorInfoMapper;
import com.hdu.edu.creditcertificatesystem.pojo.dto.FacultyInfoDTO;
import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.FacultyInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.service.FacultyInfoService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构信息接口实现类
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Slf4j
@Service
public class FacultyInfoServiceImpl implements FacultyInfoService {
    @Setter(onMethod_ = @Autowired)
    private FacultyInfoMapper facultyInfoMapper;

    @Setter(onMethod_ = @Autowired)
    private MajorInfoMapper majorInfoMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<String> create(FacultyInfoRequest facultyInfoRequest) {
        if (StringUtils.isBlank(facultyInfoRequest.getFacultyName())) {
            log.error("学院名为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "学院名为空");
        }

        // 校验学院名是否重复
        final List<FacultyInfo> list = new LambdaQueryChainWrapper<>(facultyInfoMapper)
                .eq(FacultyInfo::getFacultyName, facultyInfoRequest.getFacultyName())
                .list();
        if (CollectionUtils.isNotEmpty(list) && list.size() > 0) {
            log.error("学院名已存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "学院名已存在");
        }

        FacultyInfo facultyInfo = new FacultyInfo();
        facultyInfo.setFacultyName(facultyInfoRequest.getFacultyName());
        facultyInfoMapper.insert(facultyInfo);
        return BaseGenericsResponse.successBaseResp("创建成功");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<List<FacultyInfoDTO>> getAllList(BaseRequest baseRequest) {
        final List<FacultyInfo> facultyInfoList = facultyInfoMapper.selectList(new QueryWrapper<>());
        List<FacultyInfoDTO> facultyInfoDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(facultyInfoList)) {
            for (FacultyInfo facultyInfo : facultyInfoList) {
                FacultyInfoDTO facultyInfoDTO = new FacultyInfoDTO();
                // 查询绑定的专业
                final List<MajorInfo> majorInfoList = new LambdaQueryChainWrapper<>(majorInfoMapper)
                        .eq(MajorInfo::getFacultyId, facultyInfo.getId())
                        .list();
                if (CollectionUtils.isEmpty(majorInfoList)) {
                    facultyInfoDTO.setCount(0);
                } else {
                    facultyInfoDTO.setCount(majorInfoList.size());
                }
                facultyInfoDTO.setId(facultyInfo.getId());
                facultyInfoDTO.setFacultyName(facultyInfo.getFacultyName());
                facultyInfoDTOList.add(facultyInfoDTO);
            }
        }
        return BaseGenericsResponse.successBaseResp(facultyInfoDTOList);
    }
}
