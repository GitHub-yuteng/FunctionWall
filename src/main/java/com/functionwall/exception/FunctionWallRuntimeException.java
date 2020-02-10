/**
 * 
 */
package com.functionwall.exception;

import javax.servlet.http.HttpServletResponse;

public class FunctionWallRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -3876319857610564892L;

    private String code = "NO CODE";
    private String name = "NO NAME";
    private String message = "NO MESSAGE";
    private String error = "NO ERROR";
    private int scCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

    /**
     * 构造方法.
     *
     * @param errorCode
     *            异常码
     */
    public FunctionWallRuntimeException(FunctionWallErrorCode errorCode) {
        this(errorCode, (Object[]) null);
    }

    /**
     * 构造方法.
     *
     * @param errorCode
     *            异常码
     * @param params
     *            消息参数
     */
    public FunctionWallRuntimeException(FunctionWallErrorCode errorCode, Object... params) {
        this(errorCode, null, params);
    }

    /**
     * 构造方法.
     *
     * @param errorCode
     *            异常码
     * @param params
     *            消息参数
     * @param cause
     *            异常原因
     */
    public FunctionWallRuntimeException(FunctionWallErrorCode errorCode, Throwable cause, Object... params) {
        if (errorCode != null) {
            this.name = errorCode.name();
            this.code = errorCode.getCode();
            this.scCode = errorCode.getScCode();
            this.message = errorCode.getMessage(params);
            this.error = errorCode.getError(params);
        }
        fillInStackTrace();
        if (cause != null) {
            initCause(cause);
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getScCode() {
        return scCode;
    }

    public String getError() {
        return error;
    }
}