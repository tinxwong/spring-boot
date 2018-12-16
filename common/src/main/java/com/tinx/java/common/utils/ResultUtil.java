package com.tinx.java.common.utils;


import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.interfaces.Status;
import com.tinx.java.common.response.status.interfaces.StatusCode;

/**
 *
 */
public final class ResultUtil {

    private ResultUtil() {}

    public static <T> BaseResponse<T> makeBaseResponse(Status code) {
        return ResultUtil.makeBaseResponse(null, code);
    }

    public static <T> BaseResponse<T> makeBaseResponse(T value, Status code) {
        BaseResponse<T> info = new BaseResponse<>();
        info.setData(value);
        info.setCode(code);
        return info;
    }

    public static <T> BaseResponse<T> makeBaseResponse(T value, BaseResponse error) {
        BaseResponse<T> info = new BaseResponse<>();
        info.setData(value);
        info.setCode(error.getCode());
        return info;
    }


}
