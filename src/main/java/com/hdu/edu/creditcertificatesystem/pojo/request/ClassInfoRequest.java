package com.hdu.edu.creditcertificatesystem.pojo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 班级信息请求类
 *
 * @author chenyb46701
 * @date 2023/5/13
 */
@Getter
@Setter
@ToString
public class ClassInfoRequest extends BaseRequest{
    /**
     * 班级Id
     */
    private Integer id;

    /**
     * 专业Id
     */
    private Integer majorId;

    /**
     * 班级名
     */
    private String className;
}
