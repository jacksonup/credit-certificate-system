package com.hdu.edu.creditcertificatesystem.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 机构分页DTO
 *
 * @author chenyb46701
 * @date 2023/5/4
 */
@Getter
@Setter
@ToString
public class InstitutionPageDTO {
    /**
     * 申请表总数
     */
    private Integer count;

    /**
     * 机构信息列表
     */
    private List<InstitutionDTO> list;
}
