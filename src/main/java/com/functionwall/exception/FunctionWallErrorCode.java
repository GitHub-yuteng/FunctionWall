/**
 *
 */
package com.functionwall.exception;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum FunctionWallErrorCode {
    UNKONWN_ERROR("999999"),

    CATEGORY_IS_NULL("30001"),

    LOG_ID_IS_NULL("70001"),




    REGISTER_PASSWORD_MD5_ERROR("MD5");;

    /**
     * 创建运行时异常.<br>
     *
     * @param params
     *            异常信息参数
     * @return 运行时异常
     */
    public FunctionWallRuntimeException runtimeException(Object... params) {
        return new FunctionWallRuntimeException(this, params);
    }

    /**
     * 创建运行时异常.<br>
     *
     * @param cause
     *            异常原因
     * @param params
     *            异常信息参数
     * @return 运行时异常
     */
    public FunctionWallRuntimeException runtimeException(Throwable cause, Object... params) {
        return new FunctionWallRuntimeException(this, cause, params);
    }

    /*
     * =====================
     * =====================
     */
    private static Logger logger = LoggerFactory.getLogger(FunctionWallErrorCode.class);

    private String code;

    private int scCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

    private FunctionWallErrorCode(String code) {
        this(code, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    private FunctionWallErrorCode(String code, int scCode) {
        this.code = code;
        this.scCode = scCode;
    }

    public String getCode() {
        return code;
    }

    public int getScCode() {
        return scCode;
    }

    public String getMessage(Object... params) {
        String message = getMessage(Locale.getDefault(), params);
        int idx = message.indexOf("::");
        return idx > 0 ? message.substring(idx + 2).trim() : message.trim();
    }

    public String getError(Object... params) {
        String message = getMessage(Locale.getDefault(), params);
        int idx = message.indexOf("::");
        return idx > 0 ? message.substring(0, idx).trim() : message.trim();
    }

    private String getMessage(Locale locale, Object... params) {
        String message = "NO MESSAGE for code '" + name() + "'!!!";
        if (locale == null) {
            locale = Locale.getDefault();
        }
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("i18n.exception.Messages", locale);
            message = bundle.getString(this.name());
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
        try {
            return new MessageFormat(message).format(params);
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            return message;
        }
    }
}
