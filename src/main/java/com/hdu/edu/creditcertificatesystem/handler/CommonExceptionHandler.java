package com.hdu.edu.creditcertificatesystem.handler;

import com.hdu.edu.creditcertificatesystem.exception.BaseException;
import com.hdu.edu.creditcertificatesystem.pojo.response.BaseGenericsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获.
 * 通用MVC异常处理
 *
 * @author chenyb46701
 * @date 2023/3/31
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {

    /**
     * 通用异常处理
     *
     * @param e 异常信息
     * @return BaseGenericsResponse<String> 返回信息
     */
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public BaseGenericsResponse<String> handleBaseException(BaseException e) {
        BaseGenericsResponse<String> response = new BaseGenericsResponse<>();
        response.setCode(e.getErrorCode());
        response.setMsg(e.getErrorMessage());
        return response;
    }
}
