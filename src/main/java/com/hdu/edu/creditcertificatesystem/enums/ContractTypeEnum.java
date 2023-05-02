package com.hdu.edu.creditcertificatesystem.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 合约类型枚举
 *
 * @author chenyb46701
 * @date 2023/4/10
 */
@Getter
public enum ContractTypeEnum {
    /**
     * 用户
     */
    USER("0", "userContract"),

    /**
     * 教师
     */
    TEACHER("1", "teacherContract"),

    /**
     * 机构
     */
    INSTITUTION("2", "institutionContract");

    private static final Map<String, ContractTypeEnum> KEY_MAP = new HashMap<>(8);

    static {
        for (ContractTypeEnum contractTypeEnum : ContractTypeEnum.values()) {
            KEY_MAP.put(contractTypeEnum.getKey(), contractTypeEnum);
        }
    }

    private final String key;

    private final String value;
    ContractTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static ContractTypeEnum getByKey(String key) {
        return KEY_MAP.get(key);
    }
}
