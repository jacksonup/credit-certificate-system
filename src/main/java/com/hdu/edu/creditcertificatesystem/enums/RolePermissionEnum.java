package com.hdu.edu.creditcertificatesystem.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 角色权限枚举
 *
 * @author chenyb46701
 * @date 2023/4/23
 */
@Getter
public enum RolePermissionEnum {
    /**
     * 系统管理员
     */
    ADMIN(0, "admin"),

    /**
     * 教务管理员
     */
    EDUCATIONAL_MANAGER(1, "educational_manager"),

    /**
     * 机构审核员
     */
    INSTITUTE_MANAGER(2, "institute_manager"),

    /**
     * 机构
     */
    INSTITUTE(3, "institute"),

    /**
     * 老师
     */
    TEACHER(4, "teacher"),

    /**
     * 学生
     */
    STUDENT(5, "student");

    private static final Map<String, ContractTypeEnum> KEY_MAP = new HashMap<>(8);

    static {
        for (ContractTypeEnum contractTypeEnum : ContractTypeEnum.values()) {
            KEY_MAP.put(contractTypeEnum.getKey(), contractTypeEnum);
        }
    }

    private final Integer key;

    private final String value;
    RolePermissionEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static ContractTypeEnum getByKey(String key) {
        return KEY_MAP.get(key);
    }
}
