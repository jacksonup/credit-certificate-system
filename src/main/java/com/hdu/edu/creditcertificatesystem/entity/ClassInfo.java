package com.hdu.edu.creditcertificatesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 班级信息
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Getter
@Setter
@ToString
@TableName("class_info")
public class ClassInfo extends BaseEntity {
    /**
     * 班级名
     */
    private String className;

    /**
     * 专业Id
     */
    private Integer majorId;
}
