package com.hdu.edu.creditcertificatesystem.service.impl;

import com.hdu.edu.creditcertificatesystem.constant.ErrorCodeConstant;
import com.hdu.edu.creditcertificatesystem.contract.LessonContract;
import com.hdu.edu.creditcertificatesystem.enums.ContractTypeEnum;
import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.pojo.dto.CourseInfoDTO;
import com.hdu.edu.creditcertificatesystem.service.CourseInfoService;
import com.hdu.edu.creditcertificatesystem.spring.ContractLoader;
import com.hdu.edu.creditcertificatesystem.util.ExcelUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.tuples.generated.Tuple2;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 课程信息接口实现类
 *
 * @author chenyb46701
 * @date 2023/5/25
 */
@Slf4j
@Service("courseInfoService")
@ContractLoader(value = ContractTypeEnum.LESSON)
public class CourseInfoServiceImpl implements CourseInfoService {
    @Setter(onMethod_ = @Resource(name = "redisTemplate"))
    private ValueOperations<String, Integer> redisValueOperations;

    private LessonContract lessonContract;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(LessonContract.Lesson lesson, LessonContract.ExtraInfo extraInfo) throws Exception {
        lessonContract.save(lesson, extraInfo).send();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void importCourse(MultipartFile file) throws Exception {
        log.info("正在导入课程信息...");
        final List<Map<Integer, List<String>>> infoList = ExcelUtils.readExcel(file);
        HashSet<String> set = new HashSet<>();
        for (Map<Integer, List<String>> map : infoList) {
            for (Integer i : map.keySet()) {
                // 获取列的信息
                List<String> list = map.get(i);
                if (list.size() != 13) {
                    log.error("请输入内容，单元格不允许为空");
                    throw new BaseException(ErrorCodeConstant.CUSTOM_CODE, "请输入内容，单元格不允许为空");
                }

                // 校验课程名不可以重复
                if (set.contains(list.get(1))) {
                    log.error("");
                    continue;
                }
                set.add(list.get(1));

                // 获取主键Id
                final BASE64Encoder encoder = new BASE64Encoder();
                final BASE64Decoder decoder = new BASE64Decoder();
                final String text = list.get(0) + list.get(1);
                final byte[] textByte = text.getBytes("UTF-8");

                // 编码
                final String lessonId = encoder.encode(textByte);

                LessonContract.Lesson lesson = new LessonContract.Lesson(
                        lessonId,
                        list.get(0),
                        list.get(1),
                        list.get(2),
                        new BigInteger(list.get(3)),
                        new BigInteger(list.get(4)),
                        new BigInteger(list.get(5)),
                        new BigInteger(list.get(6)),
                        new BigInteger(list.get(9)),
                        new BigInteger(list.get(10)),
                        new BigInteger(list.get(11)),
                        new BigInteger(list.get(12))
                );

                final LessonContract.ExtraInfo extraInfo = new LessonContract.ExtraInfo(
                        String.valueOf(lessonId),
                        list.get(7).equals("是"),
                        list.get(8).equals("否")
                );

                save(lesson, extraInfo);
            }
        }
        log.info("导入课程信息成功");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CourseInfoDTO> getListByStudentId(String studentId) throws Exception {
        final Tuple2<List<LessonContract.Lesson>, List<LessonContract.ExtraInfo>> result = lessonContract.getListByStudentId(studentId).send();
        final List<LessonContract.Lesson> lessons = result.getValue1();
        final List<LessonContract.ExtraInfo> extraInfos = result.getValue2();
        List<CourseInfoDTO> courseInfoDTOS = new ArrayList<>();
        for (int i = 0; i < lessons.size(); i++) {
            if (StringUtils.isBlank(lessons.get(i).getLessonId())) {
                break;
            }

            CourseInfoDTO courseInfoDTO = new CourseInfoDTO();
            courseInfoDTO.setLessonId(lessons.get(i).getLessonId());
            courseInfoDTO.setStudentId(lessons.get(i).getStudentId());
            courseInfoDTO.setLessonName(lessons.get(i).getLessonName());
            courseInfoDTO.setOpenCollege(lessons.get(i).getOpenCollege());
            courseInfoDTO.setRegularScore(lessons.get(i).getRegularScore().intValue());
            courseInfoDTO.setMidTermScore(lessons.get(i).getMidTermScore().intValue());
            courseInfoDTO.setFinalTermScore(lessons.get(i).getFinalTermScore().intValue());
            courseInfoDTO.setFinalScore(lessons.get(i).getFinalScore().intValue());
            courseInfoDTO.setCredit(lessons.get(i).getCredit().intValue());
            courseInfoDTO.setPoint(lessons.get(i).getPoint().intValue());
            courseInfoDTO.setCreditPoint(lessons.get(i).getCreditPoint().intValue());
            courseInfoDTO.setTerm(lessons.get(i).getTerm().intValue());
            courseInfoDTO.setIsReExam(extraInfos.get(i).getIsReExam());
            courseInfoDTO.setIsRetake(extraInfos.get(i).getIsRetake());
            courseInfoDTOS.add(courseInfoDTO);
        }

        return courseInfoDTOS;
    }
}
