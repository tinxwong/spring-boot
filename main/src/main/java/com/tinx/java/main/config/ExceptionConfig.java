package com.tinx.java.main.config;

import com.tinx.java.common.exception.IllegalException;
import com.tinx.java.common.exception.RemotingException;
import com.tinx.java.common.exception.RpcException;
import com.tinx.java.common.response.BaseResponse;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.response.status.StatusInfo;
import com.tinx.java.common.response.status.interfaces.Status;
import com.tinx.java.common.response.status.interfaces.StatusCode;
import com.tinx.java.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by tinx on 2018-2-9.
 */
@ControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(ExceptionConfig.class);

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public BaseResponse exceptionHandler(Throwable ex) {
        logger.error("", ex);

        BaseResponse result = new BaseResponse();
        StatusCode code;
        if (ex instanceof RpcException) {
            code = adaptCode(((RpcException) ex).getCode());
        }
        else if (ex instanceof IllegalException) {
            Status dubboCode = ((IllegalException) ex).getStatus();
            code = new StatusInfo(dubboCode.code(), dubboCode.msg());
        }
        else if (ex instanceof RemotingException) {
            code = ResponseCode.REMOTING_EXCEPTION;
        }
        else if (ex instanceof IllegalStateException) {
            IllegalStateException stateException = (IllegalStateException) ex;
            code = new StatusInfo(ResponseCode.PARAMS_INVALID.getCode(),
                    ResponseCode.PARAMS_INVALID.getMsg() + "," + stateException.getMessage());
        }
        else {
            String msgDetail = ex.getMessage();
            if (StringUtil.isPresent(msgDetail)) {
                code = new StatusInfo(ResponseCode.EXCEPTION.code(), StringUtil.truncate(msgDetail, 200));
            }
            else {
                code = ResponseCode.UNKNOWN_EXCEPTION;
            }
        }

        result.setCode(code);
        result.setMsg(code.msg());
        return result;
    }

    private static StatusCode adaptCode(int code) {
        return code == 0 ? ResponseCode.UNKNOWN_EXCEPTION : (code == 1 ? ResponseCode.NETWORK_EXCEPTION : (code == 2 ? ResponseCode.TIMEOUT_EXCEPTION : (code == 3 ? ResponseCode.BIZ_EXCEPTION : (code == 4 ? ResponseCode.FORBIDDEN_EXCEPTION : (code == 5 ? ResponseCode.SERIALIZATION_EXCEPTION : ResponseCode.EXCEPTION)))));
    }
}
