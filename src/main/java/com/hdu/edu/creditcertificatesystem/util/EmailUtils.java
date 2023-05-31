package com.hdu.edu.creditcertificatesystem.util;

import com.hdu.edu.creditcertificatesystem.constant.HSConstants;
import com.hdu.edu.creditcertificatesystem.pojo.dto.mail.AlarmMail;
import com.hdu.edu.creditcertificatesystem.property.MailProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件工具类
 *
 * @author chenyb46701
 * @version 1.0
 * @date 2023/5/09
 **/
@Slf4j
public class EmailUtils {

    public static final String MAIL_REGULAR = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    private MailProperties mailProperties;

    private AlarmMail alarmMail;

    public EmailUtils(MailProperties mailProperties, AlarmMail alarmMail) {
        this.mailProperties = mailProperties;
        this.alarmMail = alarmMail;
    }

    /**
     * 连接失败的原因通常为以下几点, 仔细检查代码:
     * (1) 邮箱没有开启 SMTP 服务;
     * (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
     * (3) 邮箱服务器要求必须要使用 SSL 安全连接;
     * (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
     * (5) 如果以上几点都确定无误,到邮件服务器网站查找帮助。
     *
     * @throws RuntimeException 异常
     */
    public void sendMail() throws RuntimeException {
        Session session = createSession();
        // 3.创建邮件，设置收件人、抄(密)送人、主题、正文、附件信息
        InternetAddress[] receiverAddresses = getAddresses(alarmMail.getReceivers());
        InternetAddress[] copyAddresses = getAddresses(alarmMail.getCopyers());
        // 4.发送邮件
        try {
            MimeMessage message = createMimeMessage(session, mailProperties.getAccount(), receiverAddresses, copyAddresses,
                    alarmMail.getSubject(), alarmMail.getContent(), alarmMail.getAttachments());
            Transport.send(message);
            log.info("邮件发送成功");
        } catch (Exception e) {
            log.error("邮件发送失败", e);
            throw new RuntimeException("邮件发送失败", e);
        }
    }

    private Session createSession() {
        // 1.连接参数
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", mailProperties.getHost());
        props.setProperty("mail.smtp.port", mailProperties.getPort());
        // 使用的协议，一般发送邮件的协议是smtp
        props.setProperty("mail.transport.protocol", mailProperties.getProtocol());
        // 使用账号和密码认证
        props.setProperty("mail.smtp.auth", mailProperties.getAuth());
        props.setProperty("mail.smtp.ssl.enable", mailProperties.isSsl() ? HSConstants.TRUE : HSConstants.FALSE);
        // 鉴权机制
        // props.setProperty("mail.smtp.auth.mechanisms", "NTLM");
        // SMTP服务器的端口，非SSL连接的端口一般默认为 25，可以不添加；QQ邮箱的SMTP(SLL)端口为465或587
        // 如果邮箱服务器要求使用 SSL安全认证，需要改为对应邮箱的SSL连接的端口，具体可查看对应邮箱服务的帮助
        if (mailProperties.isSsl()) {
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.socketFactory.port", mailProperties.getPort());
            // props.setProperty("mail.smtp.ssl.trust", host);
            // props.setProperty("mail.smtp.ssl.protocols", mailProperties.getProtocols());
            props.setProperty("mail.smtp.ssl.trust", "*");
        }
        // 2.根据配置创建会话对象
        // <strong>Microsoft Exchange邮件服务器有坑，这里的账户名称不能带域名</strong>
        final StringBuilder tmpAccount = new StringBuilder().append(mailProperties.getAccount());
        if (HSConstants.MAIL_SERVER_TYPE_MS_EXCHANGE.equalsIgnoreCase(mailProperties.getServerType())) {
            int tmpIdx = mailProperties.getAccount().indexOf("@");
            if (tmpIdx > -1) {
                tmpAccount.delete(0, tmpAccount.length());
                tmpAccount.append(mailProperties.getAccount(), 0, tmpIdx);
            }
        }
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(tmpAccount.toString(), mailProperties.getPassword());
            }
        });
        // debug模式
        session.setDebug(false);
        return session;
    }

    private InternetAddress[] getAddresses(String receivers) {
        InternetAddress[] addresses = null;
        if (StringUtils.trimToNull(receivers) != null) {
            String[] strArray = receivers.split(",");
            addresses = new InternetAddress[strArray.length];
            int i = 0;
            for (String str : strArray) {
                int idx = str.indexOf("@");
                String name = idx > -1 ? str.substring(0, idx) : str;
                try {
                    addresses[i++] = new InternetAddress(str, name, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(String.format("邮件参数[%s]转换失败", receivers), e);
                }
            }
        }
        return addresses;
    }

    /**
     * 创建邮件
     *
     * @param session       会话
     * @param senderAddress 发件人邮箱
     * @param receivers     收件人邮箱（可以是多人）
     * @param copyers       抄送人邮箱（可以是多人），可以为空
     * @param subject       主题
     * @param content       正文
     * @param attachments   多附件，可以为空
     * @return MimeMessage
     * @throws MessagingException           异常
     * @throws UnsupportedEncodingException 异常
     */
    private static MimeMessage createMimeMessage(Session session, String senderAddress, Address[] receivers, Address[] copyers,
                                                 String subject, String content, File[] attachments)
            throws MessagingException, UnsupportedEncodingException {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人
        int idx = senderAddress.indexOf("@");
        String senderName = idx > -1 ? senderAddress.substring(0, idx) : senderAddress;
        message.setFrom(new InternetAddress(senderAddress, senderName, HSConstants.DEFAULT_CHARSET));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipients(MimeMessage.RecipientType.TO, receivers);
        // 抄送人
        if (copyers != null && copyers.length > 0) {
            message.addRecipients(MimeMessage.RecipientType.CC, copyers);
        }
        // 4. Subject: 邮件主题
        message.setSubject(subject, HSConstants.DEFAULT_CHARSET);
        // 5. Content: 邮件正文（可以使用html标签）
        Multipart multipart = new MimeMultipart();
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(content, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);
        // 添加附件
        if (attachments != null && attachments.length > 0) {
            for (File file : attachments) {
                BodyPart bodyPart = new MimeBodyPart();
                bodyPart.setDataHandler(new DataHandler(new FileDataSource(file)));
                // MimeUtility.encodeWord可以避免文件名乱码
                bodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
                multipart.addBodyPart(bodyPart);
            }
        }
        message.setContent(multipart);
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }
}
