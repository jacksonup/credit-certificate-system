package com.hdu.edu.creditcertificatesystem.processor;

import com.hdu.edu.creditcertificatesystem.pojo.dto.mail.AlarmMail;

import java.util.List;

/**
 * 抽象通知处理器
 *
 * @author chenyb46701
 * @date 2023/5/11
 */
public abstract class AbstractInformProcessor {

    public abstract void sendMail(List<AlarmMail> alarmMailList);
}
