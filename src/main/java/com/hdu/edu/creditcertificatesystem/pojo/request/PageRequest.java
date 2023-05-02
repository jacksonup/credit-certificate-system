package com.hdu.edu.creditcertificatesystem.pojo.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 分页请求
 *
 * @author chenyb46701
 * @date 2023/4/23
 */
@Getter
@Setter
@ToString
public class PageRequest extends BaseRequest{
    /**
     * 开始下标
     */
    private Integer from;

    /**
     * 分页查询扩展字段-角色
     */
    private Integer role;

    /**
     * 分页查询扩展字段-部门
     */
    private Integer sectorId;
}
