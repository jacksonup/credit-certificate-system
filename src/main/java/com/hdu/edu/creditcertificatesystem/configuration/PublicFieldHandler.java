package com.hdu.edu.creditcertificatesystem.configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动填充字段处理
 *
 * @author chenyb46701
 * @date 2023/05/12
 */
@Slf4j
@Component
public class PublicFieldHandler implements MetaObjectHandler {
    /**
     * 插入数据库操作自动填充字段
     *
     * @param metaObject 元数据
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            // 自动填充时间
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            this.setFieldValByName("modifyTime", LocalDateTime.now(), metaObject);
        } catch (Exception e) {
            log.error("insert fill fail", e);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            // 自动填充时间
            this.setFieldValByName("modifyTime", LocalDateTime.now(), metaObject);
        } catch (Exception e) {
            log.error("update fill fail", e);
        }
    }
}
