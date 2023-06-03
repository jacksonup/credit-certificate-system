package com.hdu.edu.creditcertificatesystem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdu.edu.creditcertificatesystem.constant.RedisPrefixConstants;
import com.hdu.edu.creditcertificatesystem.contract.*;
import com.hdu.edu.creditcertificatesystem.entity.ClassInfo;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.mapper.ClassInfoMapper;
import com.hdu.edu.creditcertificatesystem.mapstruct.StudentInfoConvert;
import com.hdu.edu.creditcertificatesystem.pojo.dto.mail.AlarmMail;
import com.hdu.edu.creditcertificatesystem.pojo.request.StudentInfoRequest;
import com.hdu.edu.creditcertificatesystem.property.ContractProperties;
import com.hdu.edu.creditcertificatesystem.property.MailProperties;
import com.hdu.edu.creditcertificatesystem.util.EmailUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ValueOperations;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.StaticGasProvider;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
@Slf4j
class CreditCertificateSystemApplicationTests {
    @Setter(onMethod_ = @Autowired)
    private ContractProperties contractProperties;

    @Setter(onMethod_ = @Resource(name = "stringRedisTemplate"))
    private ValueOperations<String, String> redisValueOperations;

    @Setter(onMethod_ = @Resource(name = "redisTemplate"))
    private ValueOperations<String, Integer> redisValueOperationsForLong;

    @Setter(onMethod_ = @Autowired)
    private ClassInfoMapper classInfoMapper;

    @Setter(onMethod_ = @Autowired)
    private MailProperties mailProperties;

    @Setter(onMethod_ = @Autowired)
    private StudentInfoConvert studentInfoConvert;

    /**
     * 部署合约
     * ganache不关闭，可以不用重新部署；ganache 重启需部署
     * ganache-cli -d "tackle frozen poet aware struggle ridge february merge pulse doll enhance air" -l 90000000 -e 1000 --allowUnlimitedContractSize
     *
     * @throws Exception 异常
     */
    @Test
    void contextLoads() throws Exception {
        // 监听本地链
        System.out.println(contractProperties.getHttpService());
        Web3j web3j = Web3j.build(new HttpService(contractProperties.getHttpService()));

        // 生成资格凭证
        Credentials credentials = Credentials.create(contractProperties.getCredentials());

        StaticGasProvider provider = new StaticGasProvider(
                contractProperties.getGasPrice(),
                contractProperties.getGasLimit());

        // 部署合约
        UserContract userContract = UserContract.deploy(web3j, credentials, provider).send();
        TeacherContract teacherContract = TeacherContract.deploy(web3j, credentials, provider).send();
        InstitutionContract institutionContract = InstitutionContract.deploy(web3j, credentials, provider).send();
        StudentContract studentContract = StudentContract.deploy(web3j, credentials, provider).send();
        LessonContract lessonContract = LessonContract.deploy(web3j, credentials, provider).send();

        log.info(studentContract.getContractAddress());

        // 存储合约地址到redis中
        redisValueOperations.set(ContractTypeEnum.USER.getValue(), userContract.getContractAddress());
        redisValueOperations.set(ContractTypeEnum.INSTITUTION.getValue(), institutionContract.getContractAddress());
        redisValueOperations.set(ContractTypeEnum.STUDENT.getValue(), studentContract.getContractAddress());
        redisValueOperations.set(ContractTypeEnum.TEACHER.getValue(), teacherContract.getContractAddress());
        redisValueOperations.set(ContractTypeEnum.LESSON.getValue(), lessonContract.getContractAddress());

        // 存储主键Id
        redisValueOperationsForLong.set(RedisPrefixConstants.ID + ":" + RedisPrefixConstants.INSTITUTION_INFO, 1);
        redisValueOperationsForLong.set(RedisPrefixConstants.ID + ":" + RedisPrefixConstants.LESSON_INFO, 1);

        // 创建初始化管理员账户
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        for (int i = 0; i < 5; i++) {
            UserContract.UserInfo userInfo = new UserContract.UserInfo(
                   "admin" + i,
                   "admin" + i,
                    new BigInteger("0"),
                    new BigInteger(localDateTime.format(formatter)),
                    "10086",
                    "jacksonyan999@163.com"
            );
            userContract.save(userInfo).send();
        }
        log.info("合约部署成功!");
    }

    @Test
    void testEmail() {
        AlarmMail alarmMail = new AlarmMail();
        alarmMail.setContent("测试0509hhhh");
        alarmMail.setReceivers("chenyb46701@hundsun.com");
        alarmMail.setSubject("标题哈哈哈哈");
        EmailUtils emailUtils = new EmailUtils(mailProperties, alarmMail);
        emailUtils.sendMail();
    }

    @Test
    void testRedis() {
        final String prefix = RedisPrefixConstants.ID + ":" + RedisPrefixConstants.INSTITUTION_INFO;
        final Integer andSet = redisValueOperationsForLong.getAndSet(prefix,
                redisValueOperationsForLong.get(prefix) + 1);
        System.out.println(andSet);
    }

    @Test
    void testMapper() {
        System.out.println(classInfoMapper.selectList(new QueryWrapper<>()));
        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassName("爱坤");
        classInfo.setMajorId(1);
        classInfoMapper.insert(classInfo);
    }

    @Test
    void testContract() throws Exception {
        Web3j web3j = Web3j.build(new HttpService(contractProperties.getHttpService()));

        // 生成资格凭证
        Credentials credentials = Credentials.create(contractProperties.getCredentials());

        final String s = redisValueOperations.get(ContractTypeEnum.STUDENT.getValue());

        StaticGasProvider provider = new StaticGasProvider(
                contractProperties.getGasPrice(),
                contractProperties.getGasLimit());
        StudentContract studentContract = StudentContract.load(s, web3j, credentials, provider);

        final Tuple2<List<StudentContract.StudentInfo>, List<StudentContract.ExtraInfo>> send = studentContract.getAll().send();
        StudentInfoRequest studentInfoRequest = new StudentInfoRequest();
        studentInfoRequest.setAccount("1905240");
        final Tuple2<StudentContract.StudentInfo, StudentContract.ExtraInfo> send1 = studentContract.getEntity(
                studentInfoConvert.convert(studentInfoRequest)
        ).send();

        System.out.println(1);
    }

    @Test
    void testTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        int time = 20231005;
        LocalDate createTime = LocalDate.parse(String.valueOf(time), formatter);
        System.out.println(createTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
