package com.hdu.edu.creditcertificatesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 专业信息
 * 
 * @author chenyb46701
 * @date 2023/5/12
 */
@Getter
@Setter
@ToString
@TableName("major_info")
public class MajorInfo extends BaseEntity {
    /**
     * 专业名
     */
    private String majorName;

    /**
     * 学院Id
     */
    private Integer facultyId;
}
