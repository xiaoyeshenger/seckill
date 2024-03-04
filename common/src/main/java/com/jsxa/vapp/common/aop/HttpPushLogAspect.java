package com.jsxa.vapp.common.aop;

import com.jsxa.vapp.common.annotation.HttpPushServiceLog;
import com.jsxa.vapp.common.bo.po.HttpPushLog;
import com.jsxa.vapp.common.dao.HttpPushLogDao;
import com.jsxa.vapp.common.jobManager.AsyncJobManager;
import com.jsxa.vapp.common.jobManager.factory.AsyncFactory;
import com.jsxa.vapp.common.utils.*;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;


//service层操作日志记录处理
@Slf4j
@Aspect
@Component
public class HttpPushLogAspect {

    @Resource
    private HttpPushLogDao httpPushLogDao;


    //httpPushServiceLog注解参数值的初始值
    private static Map<String,Object> initPropertyMap = new HashMap(){{
        put("httpReqUrl","");
        put("httpReqHeader","");
        put("httpReqParam","");
        put("httpResult","");
        put("deviceUuid","");
        put("productType",0L);
        put("productId",0L);
        put("productName","");
        put("regionCode","");
        put("deviceSerial","");
    }};


    //(1).正常请求，在返回结果之前加上操作日志记录
    @AfterReturning(pointcut = "@annotation(httpPushServiceLog)")
    public void doAfterReturning(JoinPoint joinPoint, HttpPushServiceLog httpPushServiceLog) {
        handleLog(joinPoint, httpPushServiceLog, null);
    }

    //(2).出现异常，捕获该异常,在全局异常处理之前加上操作日志记录
    @AfterThrowing(value = "@annotation(httpPushServiceLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, HttpPushServiceLog httpPushServiceLog, Exception e) {
        handleLog(joinPoint, httpPushServiceLog, e);
    }


    //(3).处理操作日志
    protected void handleLog(final JoinPoint joinPoint, HttpPushServiceLog httpPushServiceLog, final Exception e) {

        try {
            if (CommonUtils.isEmpty(httpPushServiceLog.regionCode())){
                return;
            }

            //(1).构建HttpPushLog
            Long time = System.currentTimeMillis();
            HttpPushLog httpPushLog = HttpPushLog.builder()
                    .status((byte)1)
                    .latestData((byte)1)
                    .pushType(Long.valueOf(httpPushServiceLog.pushType()))
                    .operateTime(time)
                    .operateDate(DateUtil.timeStamp2dateStr(time))
                    .httpReqUrl(httpPushServiceLog.httpReqUrl())
                    .httpReqHeader(httpPushServiceLog.httpReqHeader())
                    .httpReqParam(httpPushServiceLog.httpReqParam())
                    .httpResult(httpPushServiceLog.httpResult())
                    .deviceUuid(httpPushServiceLog.deviceUuid())
                    .productType(httpPushServiceLog.productType())
                    .productId(httpPushServiceLog.productId())
                    .productName(httpPushServiceLog.productName())
                    .regionCode(httpPushServiceLog.regionCode())
                    .build();
            //(2).如果出现异常(异常不为空),设置状态为失败,并保存异常信息
            if (!ObjUtil.isEmpty(e)) {
                httpPushLog.setStatus((byte)0);
                String message = e.getMessage();
                if(ObjUtil.isEmpty(message)){
                    message = e.toString();
                }
                httpPushLog.setErrorMsg(StringUtil.substring(message, 0, 2000));
            }

            //(3).获取方法路径
            //--1.获取到方法名和方法第一个参数(keyWord)
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            httpPushLog.setKeyWord(args[0].toString());
            httpPushLog.setMethodName(className + "." + methodName + "()");

            //--2.清空HttpPushServiceLog注解上的所有参数值，即设置为initPropertyMap的初始值
            AnnoUtil.dynamicallyModifyAnnotationProperty(initPropertyMap, HttpPushServiceLog.class, joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());


            //(4).保存到数据库
            AsyncJobManager.me().execute(AsyncFactory.saveHttpPushLog(httpPushLog));
        }
        catch (Exception exp) {
            log.error("处理HTTP推送日志出现异常:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }
}
