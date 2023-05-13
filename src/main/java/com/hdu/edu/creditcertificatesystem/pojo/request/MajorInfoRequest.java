package com.hdu.edu.creditcertificatesystem.pojo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 专业信息请求
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Getter
@Setter
@ToString
public class MajorInfoRequest extends BaseRequest{
    /**
     * 学院Id
     */
    private Integer facultyId;

    /**
     * 专业名
     */
    private String majorName;
}
