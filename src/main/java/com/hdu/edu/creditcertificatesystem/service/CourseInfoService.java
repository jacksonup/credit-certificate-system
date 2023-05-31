package com.hdu.edu.creditcertificatesystem.service;

import com.hdu.edu.creditcertificatesystem.contract.LessonContract;
import com.hdu.edu.creditcertificatesystem.pojo.dto.CourseInfoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 课程信息接口
 *
 * @author chenyb46701
 * @date 2023/5/25
 */
public interface CourseInfoService {
    /**
     * 保存课程信息
     *
     * @param lesson 课程信息
     * @param extraInfo 课程额外信息
     */
    void save(LessonContract.Lesson lesson, LessonContract.ExtraInfo extraInfo);

    /**
     * 导入课程信息
     *
     * @param file 文件
     */
    void importCourse(MultipartFile file);

    /**
     * 根据学生Id获取课程信息
     *
     * @param studentId 学生Id
     * @return 课程信息DTO
     */
    List<CourseInfoDTO> getListByStudentId(String studentId);
}
