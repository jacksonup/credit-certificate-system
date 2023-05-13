package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 专业信息DTO
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Getter
@Setter
@ToString
public class MajorInfoDTO {
    /**
     * 主键Id
     */
    private Integer id;

    /**
     * 专业名
     */
    private String majorName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 班级数量
     */
    private Integer count;
}
