package com.hdu.edu.creditcertificatesystem.service.impl;

import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.constant.RedisPrefixConstants;
import com.hdu.edu.creditcertificatesystem.contract.InstitutionContract;
import com.hdu.edu.creditcertificatesystem.enums.AuditTypeEnum;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.enums.RolePermissionEnum;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.mapstruct.InstitutionConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.InstitutionDTO;
import com.hdu.edu.creditcertificatesystem.pojo.dto.mail.AlarmContent;
import com.hdu.edu.creditcertificatesystem.pojo.dto.mail.AlarmMail;
import com.hdu.edu.creditcertificatesystem.pojo.request.InstitutionRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.PageRequest;
import com.hdu.edu.creditcertificatesystem.pojo.request.UserInfoRequest;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import com.hdu.edu.creditcertificatesystem.processor.AuditInformProcessor;
import com.hdu.edu.creditcertificatesystem.service.InstitutionService;
import com.hdu.edu.creditcertificatesystem.service.UserService;
import com.hdu.edu.creditcertificatesystem.spring.CloudComponent;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.exceptions.ContractCallException;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * 机构信息接口实现
 *
 * @author chenyb46701
 * @date 2023/5/1
 */
@Slf4j
@Service("institutionService")
@ContractLoader(value = ContractTypeEnum.INSTITUTION)
@CloudComponent
public class InstitutionServiceImpl implements InstitutionService {
    @Setter(onMethod_ = @Autowired)
    private InstitutionConvert baseConvert;

    @Setter(onMethod_ = @Autowired)
    private UserService userService;

    @Setter(onMethod_ = @Resource(name = "redisTemplate"))
    private ValueOperations<String, Integer> redisValueOperations;

    @Setter(onMethod_ = @Autowired)
    private AuditInformProcessor auditInformProcessor;

    private InstitutionContract institutionContract;

    /**
     * {@inheritDoc}
     */
    @Override
    public InstitutionDTO getDTO(InstitutionRequest institutionRequest) throws Exception {
        final Tuple2<InstitutionContract.InstitutionInfo, InstitutionContract.ExtraInfo> result =
                institutionContract.getEntity(baseConvert.convert(institutionRequest)).send();
        if (null == result) {
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "查询机构信息异常");
        }
        final InstitutionContract.InstitutionInfo institutionInfo = result.getValue1();
        final InstitutionContract.ExtraInfo extraInfo = result.getValue2();
        if (StringUtils.isEmpty(institutionInfo.getId()) || StringUtils.isEmpty(extraInfo.getId())) {
            log.error("机构信息不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "机构信息不存在");
        }

        return baseConvert.convertEx(institutionInfo, extraInfo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<InstitutionDTO> getListPage(PageRequest pageRequest) throws Exception {
        final Tuple2<List<InstitutionContract.InstitutionInfo>, List<InstitutionContract.ExtraInfo>> listListTuple2 =
                institutionContract.getListPage(
                        new BigInteger(String.valueOf(pageRequest.getFrom())),
                        new BigInteger(String.valueOf(pageRequest.getFrom()) + 9)).send();
        if (null == listListTuple2) {
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "分页查询机构信息异常");
        }
        final List<InstitutionContract.InstitutionInfo> institutionInfoList = listListTuple2.getValue1();
        final List<InstitutionContract.ExtraInfo> extraInfoList = listListTuple2.getValue2();
        if (CollectionUtils.isEmpty(institutionInfoList) || CollectionUtils.isEmpty(extraInfoList)) {
            log.error("机构信息不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "机构信息不存在");
        }

        List<InstitutionDTO> list = new ArrayList<>();
        for (int i = 0; i < institutionInfoList.size(); i++) {
            list.add(baseConvert.convertEx(institutionInfoList.get(i), extraInfoList.get(i)));
        }

        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<String> apply(InstitutionRequest institutionRequest) throws Exception {
        final Tuple2<InstitutionContract.InstitutionInfo, InstitutionContract.ExtraInfo> result =
                institutionContract.getByName(institutionRequest.getInstitutionName()).send();
        final InstitutionContract.InstitutionInfo institutionInfo = result.getValue1();
        final InstitutionContract.ExtraInfo extraInfo = result.getValue2();

        if (StringUtils.isBlank(institutionInfo.getId()) || StringUtils.isBlank(extraInfo.getId())) {
            // 获取主键Id
            final String prefix = RedisPrefixConstants.ID + ":" + RedisPrefixConstants.INSTITUTION_INFO;
            institutionRequest.setId(String.valueOf(redisValueOperations.getAndSet(prefix,
                    redisValueOperations.get(prefix) + 1)));

            // 设置证明照片url
            final List<String> proofPictures = institutionRequest.getProofPictures();
            if (CollectionUtils.isEmpty(proofPictures)) {
                log.error("申请机构入驻请上传证明照片");
                throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "申请机构入驻请上传证明照片");
            }

            StringBuilder sb = new StringBuilder();
            for (String s : proofPictures) {
                sb.append(s).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            institutionRequest.setAuthorCertificatePic(sb.toString());

            // 设置学院Id
            final List<Long> faculties = institutionRequest.getFaculties();
            if (CollectionUtils.isEmpty(faculties)) {
                log.error("申请机构入驻请设置权限学院Ids");
                throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "申请机构入驻请设置权限学院Id");
            }

            StringBuilder sbFOrFaculty = new StringBuilder();
            for (Long l : faculties) {
                sbFOrFaculty.append(l).append(",");
            }
            sbFOrFaculty.deleteCharAt(sbFOrFaculty.length() - 1);
            institutionRequest.setFacultyId(sbFOrFaculty.toString());

            // 设置专业Id
            final List<Long> majors = institutionRequest.getMajors();
            if (CollectionUtils.isEmpty(majors)) {
                log.error("申请机构入驻请设置专业Ids");
                throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "申请机构入驻请设置专业Ids");
            }
            StringBuilder sbForMajor = new StringBuilder();
            for (Long l : majors) {
                sbForMajor.append(l).append(",");
            }
            sbForMajor.deleteCharAt(sbForMajor.length() - 1);
            institutionRequest.setMajorId(sbForMajor.toString());

            // 设置状态
            institutionRequest.setStatus(AuditTypeEnum.WAIT_AUDIT.getKey());

            // 设置入驻时间
            institutionRequest.setCreateTime((LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))));

            institutionContract.save(baseConvert.convert(institutionRequest), baseConvert.convertEx(institutionRequest)).send();
            log.info("机构申请成功");
            return BaseGenericsResponse.successBaseResp("申请发起成功，请耐心等待审核，审核通过后将发放账号至邮箱");
        } else {
            if (extraInfo.getStatus().intValue() == AuditTypeEnum.ACCESS.getKey()) {
                log.info("机构已审核通过");
                return BaseGenericsResponse.failureBaseResp(ErrorCodeConstant.CUSTOM_CODE, "贵企业 / 组织已入驻，请勿重复申请");
            } else if (extraInfo.getStatus().intValue() == AuditTypeEnum.WAIT_AUDIT.getKey()){
                log.info("机构待审核");
                return BaseGenericsResponse.failureBaseResp(ErrorCodeConstant.CUSTOM_CODE, "贵企业 / 组织已提交申请，请耐心等待审核，审核通过后将发放账号至邮箱");
            }
        }

        return BaseGenericsResponse.failureBaseResp(ErrorCodeConstant.CUSTOM_CODE, "申请失败");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(InstitutionRequest institutionRequest) throws Exception {
        institutionContract.save(baseConvert.convert(institutionRequest),
                baseConvert.convertEx(institutionRequest)).send();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(InstitutionRequest institutionRequest) throws Exception {
        institutionContract.deleteStudent(baseConvert.convert(institutionRequest)).send();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<InstitutionDTO> getListPageByStatus(PageRequest pageRequest) throws Exception {
        Tuple2<List<InstitutionContract.InstitutionInfo>, List<InstitutionContract.ExtraInfo>> listTuple2;
        try {
            listTuple2 = institutionContract.getListPageByStatus(
                    new BigInteger(String.valueOf(pageRequest.getStatus())),
                    new BigInteger(String.valueOf(pageRequest.getFrom())),
                    new BigInteger("10")).send();
        } catch (ContractCallException e) {
            log.error("数据为空");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "数据为空");
        }

        final List<InstitutionContract.InstitutionInfo> institutionInfoList = listTuple2.getValue1();
        final List<InstitutionContract.ExtraInfo> extraInfoList = listTuple2.getValue2();
        if (CollectionUtils.isEmpty(institutionInfoList) || CollectionUtils.isEmpty(extraInfoList)) {
            log.error("机构信息不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "机构信息不存在");
        }

        List<InstitutionDTO> list = new ArrayList<>();
        for (int i = 0; i < institutionInfoList.size(); i++) {
            list.add(baseConvert.convertEx(institutionInfoList.get(i), extraInfoList.get(i)));
        }

        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<String> reject(InstitutionRequest institutionRequest) throws Exception {
        // 审核通过
        final Tuple2<InstitutionContract.InstitutionInfo, InstitutionContract.ExtraInfo> result =
                institutionContract.getEntity(baseConvert.convert(institutionRequest)).send();
        if (null == result) {
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "查询机构信息异常");
        }
        final InstitutionContract.InstitutionInfo institutionInfo = result.getValue1();
        final InstitutionContract.ExtraInfo extraInfo = result.getValue2();
        if (StringUtils.isBlank(institutionInfo.getId()) || StringUtils.isBlank(extraInfo.getId())) {
            log.error("机构信息不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "机构信息不存在");
        }
        if (StringUtils.isBlank(institutionInfo.getInstitutionEmail())) {
            log.error("未绑定邮件");
            throw new BaseException("未绑定邮件");
        }

        // 已经审核通过了不可驳回
        if (extraInfo.getStatus().intValue() == AuditTypeEnum.ACCESS.getKey()) {
            log.info("该机构已经审核通过不可驳回");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "该机构已经审核通过不可驳回");
        }

        // 审核驳回，删除信息
        delete(institutionRequest);

        // 构造邮件信息
        List<AlarmMail> alarmMailList = new ArrayList<>();
        AlarmMail alarmMail = new AlarmMail();
        AlarmContent alarmContent = new AlarmContent();
        alarmContent.setIsPass(false);
        alarmContent.setRejectReason(institutionRequest.getReason());
        alarmMail.setAlarmContent(alarmContent);
        alarmMail.setReceivers(institutionInfo.getInstitutionEmail());
        alarmMailList.add(alarmMail);

        // 异步发送邮件
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.execute(() -> auditInformProcessor.sendMail(alarmMailList));
        return BaseGenericsResponse.successBaseResp("驳回成功");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseGenericsResponse<String> pass(InstitutionRequest institutionRequest) throws Exception {
        // 审核通过
        final Tuple2<InstitutionContract.InstitutionInfo, InstitutionContract.ExtraInfo> result =
                institutionContract.getEntity(baseConvert.convert(institutionRequest)).send();
        if (null == result) {
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "查询机构信息异常");
        }
        final InstitutionContract.InstitutionInfo institutionInfo = result.getValue1();
        final InstitutionContract.ExtraInfo extraInfo = result.getValue2();
        if (ObjectUtils.isEmpty(institutionInfo) || ObjectUtils.isEmpty(extraInfo)) {
            log.error("机构信息不存在");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "机构信息不存在");
        }

        // 如果已经审核通过不重复审核
        if (extraInfo.getStatus().intValue() == AuditTypeEnum.ACCESS.getKey()) {
            log.info("该机构已审核通过");
            throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "该机构已审核通过，请勿重复审核");
        }

        // 审核通过
        institutionInfo.setAccount(institutionInfo.getId() + "10000");
        institutionInfo.setPassword(institutionInfo.getId() + "10000");
        extraInfo.setAuditTime(new BigInteger(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))));
        extraInfo.setStatus(new BigInteger(String.valueOf(AuditTypeEnum.ACCESS.getKey())));

        final InstitutionContract.InstitutionInfo newInstitutionInfo = new InstitutionContract.InstitutionInfo(
                institutionInfo.getId(),
                institutionInfo.getFacultyId(),
                institutionInfo.getMajorId(),
                institutionInfo.getAccount(),
                institutionInfo.getPassword(),
                institutionInfo.getInstitutionName(),
                institutionInfo.getInstitutionPhone(),
                institutionInfo.getInstitutionEmail(),
                institutionInfo.getInstitutionPlace()
        );

        final InstitutionContract.ExtraInfo newExtraInfo = new InstitutionContract.ExtraInfo(
                extraInfo.getId(),
                extraInfo.getStatus(),
                extraInfo.getAuthorCertificatePic(),
                extraInfo.getCreateTime(),
                extraInfo.getAuditTime(),
                extraInfo.getMessage(),
                extraInfo.getReason()
        );

        institutionContract.save(newInstitutionInfo, newExtraInfo).send();


        // 生成账号和密码
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setAccount(institutionInfo.getId() + "10000");
        userInfoRequest.setRole(RolePermissionEnum.INSTITUTE.getKey());
        userInfoRequest.setPassword(institutionInfo.getId() + "10000");
        userInfoRequest.setPhone(institutionInfo.getInstitutionPhone());
        userInfoRequest.setEmail(institutionInfo.getInstitutionEmail());
        userInfoRequest.setCreateTime((LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))));
        userService.save(userInfoRequest);

        // 构造邮件信息
        List<AlarmMail> alarmMailList = new ArrayList<>();
        AlarmMail alarmMail = new AlarmMail();
        AlarmContent alarmContent = new AlarmContent();
        alarmContent.setIsPass(true);
        alarmContent.setAccount(institutionInfo.getId() + "10000");
        alarmContent.setPassword(institutionInfo.getId() + "10000");
        alarmMail.setAlarmContent(alarmContent);
        alarmMail.setReceivers(institutionInfo.getInstitutionEmail());
        alarmMailList.add(alarmMail);

        // 异步发送邮件
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.execute(() -> auditInformProcessor.sendMail(alarmMailList));
        return BaseGenericsResponse.successBaseResp("通过成功");
    }
}
