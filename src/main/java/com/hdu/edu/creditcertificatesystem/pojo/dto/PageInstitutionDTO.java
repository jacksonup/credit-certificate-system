package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 分页机构信息DTO
 *
 * @author chenyb46701
 * @date 2023/5/28
 */
@Getter
@Setter
@ToString
public class PageInstitutionDTO {
    /**
     * 机构信息列表
     */
    private List<InstitutionDTO> institutions;

    /**
     * 机构总数
     */
    private Integer count;
}
