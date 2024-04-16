package com.zy.seckill.common.config;

import com.zy.seckill.common.utils.ObjUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@Configuration
public class FeignTokenInterceptor implements RequestInterceptor {

    public FeignTokenInterceptor() {}

    @Override
    public void apply(RequestTemplate requestTemplate) {
        //1.向后传递seata的xid，以便实现分布式事务
        /*String xid = RootContext.getXID();
        if(!ObjUtil.isEmpty(xid)){
            requestTemplate.header(RootContext.KEY_XID, xid);
        }*/

        //2.其他header有的话也一并传递
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes != null){
            HttpServletRequest request = attributes.getRequest();
            Map<String, String[]> parameterMap = request.getParameterMap();
            if(request != null){
                //1.header中只向后传递AUTHORIZATION
                //requestTemplate.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));

                //2.传递所有的header
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String values = request.getHeader(name);
                        requestTemplate.header(name, values);
                    }
                }
            }
        }
    }
}
