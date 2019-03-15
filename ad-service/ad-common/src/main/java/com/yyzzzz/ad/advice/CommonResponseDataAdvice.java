package com.yyzzzz.ad.advice;

import com.yyzzzz.ad.annotation.IgnoreResponseAdvice;
import com.yyzzzz.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by yyzzzz on 2019/3/13.
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) return false;
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) return false;
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        CommonResponse<Object> commonResponse = new CommonResponse<>(0, "");
        if(null == o) return null;
        else if(o instanceof CommonResponse) commonResponse = (CommonResponse<Object>)o;
        else commonResponse.setData(o);
        return commonResponse;
    }
}
