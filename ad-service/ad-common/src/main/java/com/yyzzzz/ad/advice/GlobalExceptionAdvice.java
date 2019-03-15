package com.yyzzzz.ad.advice;

import com.yyzzzz.ad.exception.AdException;
import com.yyzzzz.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yyzzzz on 2019/3/14.
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handleAdException(HttpServletRequest request, AdException ex){
        CommonResponse<String> response = new CommonResponse<>(-1 ,"business error");
        response.setData(ex.getMessage());
        return response;
    }
}
