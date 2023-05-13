package com.hdu.edu.creditcertificatesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 学院信息
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Getter
@Setter
@ToString
@TableName("faculty_info")
public class FacultyInfo extends BaseEntity {
    /**
     * 学院名
     */
    private String facultyName;
}
