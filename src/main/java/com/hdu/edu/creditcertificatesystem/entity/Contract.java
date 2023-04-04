package com.hdu.edu.creditcertificatesystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 维护部署的合约地址
 *
 * @author chenyb46701
 * @date 2022/9/27
 */
@Setter
@Getter
@ToString
public class Contract {
    /**
     * id 自增
     */
    @TableId(type = IdType.AUTO)
    private int id;

    /**
     * 合约名
     */
    private String contractName;

    /**
     * 合约地址
     */
    private String contractAddress;
}
