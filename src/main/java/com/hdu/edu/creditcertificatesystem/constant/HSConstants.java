
package com.hdu.edu.creditcertificatesystem.constant;

/**
 * 常量接口
 *
 * @author studio-auto
 * @since 20220323
 **/
public interface HSConstants {

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static final String TRUE = "true";
    public static final String FALSE = "false";

    /**
     * Microsoft Exchange邮件服务类型
     */
    public static final String MAIL_SERVER_TYPE_MS_EXCHANGE = "ms_exchange";

    /**
     * 默认页码编码
     */
    int CNST_DEFAULT_PAGE_NO = 1;

    /**
     * 默认每页显示记录数
     */
    int CNST_DEFAULT_PAGE_SIZE = 10;

    /**
     * 最大每页显示记录数
     */
    int CNST_MAX_PAGE_SIZE = 1000;

    /**
     * 普通户
     **/
    String CNST_ORGANFLAG_COMMON = "0";

    /**
     * 机构户
     **/
    String CNST_ORGANFLAG_ORGAN = "1";

    /**
     * 产品户
     **/
    String CNST_PRODFLAG_COMMON = "3";
}
