package com.hdu.edu.creditcertificatesystem.enums;

import lombok.Getter;
import org.checkerframework.checker.units.qual.K;

import java.util.HashMap;
import java.util.Map;

/**
 * 审核状态
 *
 * @author chenyb46701
 * @date 2023/4/24
 */
@Getter
public enum AuditTypeEnum {
    /**
     * 待审核
     */
    WAIT_AUDIT(0, "待审核"),

    /**
     * 已驳回
     */
    REJECT(1, "驳回"),

    /**
     * 已通过
     */
    ACCESS(2, "已通过");

    public static Map<Integer, AuditTypeEnum> getKeyMap() {
        return KEY_MAP;
    }

    private static final Map<Integer, AuditTypeEnum> KEY_MAP = new HashMap<>(6);

    static {
        for (AuditTypeEnum auditTypeEnum : AuditTypeEnum.values()) {
            KEY_MAP.put(auditTypeEnum.getKey(), auditTypeEnum);
        }
    }

    private final Integer key;

    private final String value;

    AuditTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static AuditTypeEnum getByKey(Integer key) {
        return KEY_MAP.get(key);
    }
}
