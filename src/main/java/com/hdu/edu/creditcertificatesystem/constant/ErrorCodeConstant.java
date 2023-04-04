package com.hdu.edu.creditcertificatesystem.constant;

/**
 * 通用异常代码定义
 *
 * @author chenyb46701
 * @date 2023/04/02
 */
public interface ErrorCodeConstant {
    /**
     * {0}
     */
    String CUSTOM_CODE = "100000";

    /**
     * 发生异常
     */
    String FIXED_CODE = "100001";

    /**
     * {0}请求失败
     */
    String REQUEST_CODE = "100002";

    /**
     * 更新[{0}[{1}]失败
     */
    String UPDATE_ERROR_CODE = "100003";

    /**
     * 删除[{0}[{1}]失败
     */
    String DELETE_ERROR_CODE = "100004";

    /**
     * {0}[{1}]不存在
     */
    String NO_EXIST_CODE = "100041";

    /**
     * {0}[{1}]已存在
     */
    String REPEAT_CODE = "100042";

    /**
     * 参数[{0}]不能为空
     */
    String PARAM_VALID_CODE = "100011";
}
