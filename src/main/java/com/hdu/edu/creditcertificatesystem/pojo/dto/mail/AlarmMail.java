package com.hdu.edu.creditcertificatesystem.pojo.dto.mail;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

/**
 * 预警内容
 *
 * @author zuowei44676
 * @version 1.0
 * @date 2022/9/17
 **/
@Getter
@Setter
@ToString
public class AlarmMail {

    /** 对象 */
    private AlarmContent alarmContent;

    /** 收件人邮箱地址（多人），必填 */
    private String receivers;

    /** 邮件主题，必填 */
    private String subject;

    /** 邮件正文，必填 */
    private String content;

    /** 抄送人邮箱地址（多人） */
    private String copyers;

    /** 附件 */
    private File[] attachments;
}
