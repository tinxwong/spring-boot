package com.tinx.java.main.advice;

import com.tinx.java.common.exception.IllegalException;
import com.tinx.java.common.response.status.ResponseCode;
import com.tinx.java.common.response.status.interfaces.Status;
import com.tinx.java.common.utils.ResultUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author tinx
 * @date 2018-12-12 18:01
 */
//@ControllerAdvice
public class ResultHandle implements ResponseBodyAdvice<Object> {
//    @Nullable
//    @Override
//    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        return null;
//    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (object instanceof IllegalException) {
            Status status = ((IllegalException) object).getStatus();
            ResultUtil.makeBaseResponse("",status);
        }
//        return ResultUtil.success(object);
        return ResultUtil.makeBaseResponse(object, ResponseCode.SUCCESS);
    }
}