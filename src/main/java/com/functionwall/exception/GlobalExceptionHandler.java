package com.functionwall.exception;

import com.functionwall.pojo.vo.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(FunctionWallRuntimeException.class);

    @ExceptionHandler(value = FunctionWallRuntimeException.class)
    @ResponseBody
    public APIResponse businessException(Exception e) {

        String msg = "请求错误";
        if (e instanceof FunctionWallRuntimeException) {
            msg = ((FunctionWallRuntimeException) e).getCode();
        }
        logger.error("find exception:e={}", e.getMessage());
        e.printStackTrace();
        return APIResponse.fail(msg);
    }
}