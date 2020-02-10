package com.functionwall.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {

    private static final String CODE_SUCCESS = "success";
    private static final String CODE_FAIL = "fail";

    private String msg;
    private String code;
    private T data;

    public APIResponse(String code) {
        this.code = code;
    }

    public APIResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public APIResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static APIResponse success() {
        return new APIResponse(CODE_SUCCESS);
    }

    public static APIResponse success(Object data) {
        return new APIResponse(CODE_SUCCESS, data);
    }

    public static APIResponse fail(String msg) {
        return new APIResponse(CODE_FAIL, msg);
    }

    public static APIResponse errorCode(String errorCode) {
        return new APIResponse(errorCode);
    }
}
