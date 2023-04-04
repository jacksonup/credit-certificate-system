package com.hdu.edu.creditcertificatesystem.pojo.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 通用泛型响应，用于承载一些基本类型
 *
 * @author chenyb46701
 * @date 2023/3/13
 */
@Getter
@Setter
@ToString
public class BaseGenericsResponse<T> extends BaseResponse{
    /**
     * 数据
     */
    private T data;

    public static <T> BaseGenericsResponse<T> successBaseResp(T data) {
        BaseGenericsResponse<T> baseGenericsResponse = new BaseGenericsResponse<>();
        baseGenericsResponse.success(data);
        return baseGenericsResponse;
    }

    public BaseGenericsResponse<T> success(T data) {
        this.setCode(SUCCESS_STATUS);
        this.setMsg("ok");
        this.data = data;
        return this;
    }

    public static <T> BaseGenericsResponse<T> failureBaseResp(String i, String info) {
        BaseGenericsResponse<T> BaseGenericsResponse = new BaseGenericsResponse();
        BaseGenericsResponse.failureMsg(i, info);
        return BaseGenericsResponse;
    }

    public static <T> BaseGenericsResponse<T> failureBaseRespWithCode(String errorCode, String info) {
        BaseGenericsResponse<T> BaseGenericsResponse = new BaseGenericsResponse<>();
        BaseGenericsResponse.setCode(errorCode);
        BaseGenericsResponse.setMsg(info);
        return BaseGenericsResponse;
    }

    public BaseGenericsResponse failureMsg(String i, String info) {
        this.setCode(i);
        this.setMsg(info);
        return this;
    }
}
