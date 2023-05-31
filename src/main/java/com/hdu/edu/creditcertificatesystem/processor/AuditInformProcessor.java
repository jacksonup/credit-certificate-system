package com.hdu.edu.creditcertificatesystem.processor;

import com.hdu.edu.creditcertificatesystem.pojo.dto.mail.AlarmContent;
import com.hdu.edu.creditcertificatesystem.pojo.dto.mail.AlarmMail;
import com.hdu.edu.creditcertificatesystem.property.MailProperties;
import com.hdu.edu.creditcertificatesystem.util.EmailUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.List;

/**
 * 审核通知处理器
 *
 * @author chenyb46701
 * @date 2023/5/11
 */
@Slf4j
@Component
public class AuditInformProcessor extends AbstractInformProcessor {
    @Setter(onMethod_ = @Autowired)
    private MailProperties mailProperties;

    /**
     * 邮件标题模版
     */
    private static final String MAIL_TITLE_TEMPLATE = "【杭电】学分证明信用体制-机构申请审核情况";

    /**
     * 邮件通过正文模板
     */
    private static final String MAIL_PASS_CONTENT_TEMPLATE = "您好：<br>\n" +
            "&nbsp;&nbsp;杭电-学分证明信用体制-机构申请已通过，以下是登录信息：<br>\n" +
            "&nbsp;&nbsp;账号：{0}<br>\n" +
            "&nbsp;&nbsp;密码：{1}<br>\n" +
            "&nbsp;&nbsp;您可以登录系统进行查看学生信息，请将账号密码保存好，切勿泄露给他人。\n";

    /**
     * 邮件驳回正文模板
     */
    private static final String MAIL_REJECT_CONTENT_TEMPLATE = "您好：<br>\n" +
            "&nbsp;&nbsp;很遗憾，杭电-学分证明信用体制-机构入驻申请未通过：<br>\n" +
            "&nbsp;&nbsp;驳回原因：{0}<br>\n" +
            "&nbsp;&nbsp;请认真查看驳回原因，您可以在系统中重新提交入驻申请。\n";


    /**
     * 邮件发送
     *
     * @param alarmMailList 邮件发送内容
     */
    @Override
    public void sendMail(List<AlarmMail> alarmMailList) {
        log.info("正在进行审核情况邮件发送...");
        int errorCount = 0;
        log.info("邮件配置信息:{}", mailProperties.toString());
        for (AlarmMail alarmMail : alarmMailList) {
            alarmMail.setSubject(MAIL_TITLE_TEMPLATE);

            // 设置邮件内容
            if (alarmMail.getAlarmContent().getIsPass()) {
                alarmMail.setContent(generatePassMailContent(MAIL_PASS_CONTENT_TEMPLATE, alarmMail.getAlarmContent()));
            } else {
                alarmMail.setContent(generateRejectMailContent(MAIL_REJECT_CONTENT_TEMPLATE, alarmMail.getAlarmContent()));
            }

            EmailUtils email = new EmailUtils(mailProperties, alarmMail);
            try {
                log.info("邮件发送中...");
                email.sendMail();
            } catch (Exception e) {
                errorCount++;
                log.error("邮件发送失败，错误消息：{}", e.getMessage(), e);
            }
        }
        log.info("邮件通知结束.");
        String msg = String.format("发送成功%s, 发送失败%s.", alarmMailList.size() - errorCount, errorCount);
        log.info(msg);
        if (errorCount > 0) {
            throw new RuntimeException(msg);
        }
    }

    /**
     * 生成通过邮件内容
     *
     * @param mailContentTemplate 邮件正文模版
     * @param alarmContent 内容对象
     * @return 正文
     */
    public String generatePassMailContent(String mailContentTemplate, AlarmContent alarmContent) {
        return MessageFormat.format(
                mailContentTemplate,
                alarmContent.getAccount(),
                alarmContent.getPassword()
        );
    }

    /**
     * 生成驳回邮件内容
     *
     * @param mailContentTemplate 邮件正文模版
     * @param alarmContent 内容对象
     * @return 正文
     */
    public String generateRejectMailContent(String mailContentTemplate, AlarmContent alarmContent) {
        return MessageFormat.format(
                mailContentTemplate,
                alarmContent.getRejectReason()
        );
    }
}
