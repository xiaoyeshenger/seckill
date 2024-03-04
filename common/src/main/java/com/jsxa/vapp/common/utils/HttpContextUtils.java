package com.jsxa.vapp.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/*
 * @Author: zhangyong
 * description: HttpContextUtils
 * @Date: 2020-06-11 09:17
 * @Param:
 * @Return:
 */
public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {

        String accept = request.getHeader("accept");
        String xRequestedWith = request.getHeader("X-Requested-With");

        // 如果是异步请求或是手机端，则直接返回信息
        return ((accept != null && accept.contains("application/json")
                || (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest"))
        ));
    }
}
