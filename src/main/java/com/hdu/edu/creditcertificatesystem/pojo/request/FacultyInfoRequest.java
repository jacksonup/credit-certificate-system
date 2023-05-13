package com.hdu.edu.creditcertificatesystem.pojo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 学院信息请求类
 *
 * @author chenyb46701
 * @date 2023/5/12
 */
@Getter
@Setter
@ToString
public class FacultyInfoRequest extends BaseRequest {
    /**
     * 学院id
     */
    private Integer id;

    /**
     * 学院名
     */
    private String facultyName;
}
