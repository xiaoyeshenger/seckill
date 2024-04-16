package com.zy.seckill.inventory.util;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.zy.seckill.common.utils.*;
import com.zy.seckill.inventory.bo.po.XxlJobInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangyong
 * @description /jobinfo/addJob,/jobinfo/removeJob,/jobgroup/loadByAppName
 *              三个方法需要提前在xxx-job-admin源码中新增后才能使用。
 * @date 2023/12/27 17:30
 * @param 
 * @return 
 */
@Slf4j
public class XxlJobUtil {

    private static final String ADD_INFO_URL = "/jobinfo/addJob";
    private static final String UPDATE_INFO_URL = "/jobinfo/updateJob";
    private static final String REMOVE_INFO_URL = "/jobinfo/removeJob";
    private static final String GET_GROUP_ID = "/jobgroup/loadByAppName";


    /**
     * 向xxl-job-admin添加定时任务
     * corn : corn
     */
    public static String addJob(String xxlJobAdminAddress,String corn,String handler,String handlerParam,String jobDesc,String appName) {

        //1.获取到jobGroupId
        Integer jobGroupId = getJobGroupIdByAppName(xxlJobAdminAddress,appName);

        //2.构建
        XxlJobInfo xxlJobInfo = buildXxlJobInfo(corn, handler,handlerParam,jobGroupId,jobDesc);

        //3.通过http post接口向xxl-job-admin添加定时任务
        //(1).请求头
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("Authorization","Basic YWRtaW46MTIzNDU2");

        //(2).请求参数
        String xxlJobInfoJson = JSONUtil.toJsonStr(xxlJobInfo);
        JSONObject jsonObject = HttpClientUtil.doPostJsonHeader(xxlJobAdminAddress + ADD_INFO_URL, xxlJobInfoJson,headerMap);
        if (ObjUtil.isEmpty(jsonObject)) {
            throw new IllegalArgumentException("创建定时任务失败("+handler+")");
        }

        //(3).判断响应结果
        Integer code = jsonObject.getInteger("code");
        if (code != 200) {
            String msg = jsonObject.getString("msg");
            log.info("创建定时任务失败{},errorCode:{},msg:{}","("+handler+")",code,msg);
            throw new IllegalArgumentException(msg);
        }

        String jobId = jsonObject.getString("content");
        return jobId;
    }

    /**
     * 向xxl-job-admin更新定时任务
     * corn : corn
     */
    public static Integer updateJob(int jobId,String xxlJobAdminAddress,String corn,String handler,String handlerParam,String jobDesc,String appName) {

        //1.获取到jobGroupId
        Integer jobGroupId = getJobGroupIdByAppName(xxlJobAdminAddress,appName);

        //2.构建
        XxlJobInfo xxlJobInfo = buildXxlJobInfo(corn, handler,handlerParam,jobGroupId,jobDesc);
        xxlJobInfo.setId(jobId);

        //3.通过http post接口向xxl-job-admin添加定时任务
        //(1).请求头
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("Authorization","Basic YWRtaW46MTIzNDU2");

        //(2).请求参数
        String xxlJobInfoJson = JSONUtil.toJsonStr(xxlJobInfo);
        JSONObject jsonObject = HttpClientUtil.doPostJsonHeader(xxlJobAdminAddress + UPDATE_INFO_URL, xxlJobInfoJson,headerMap);
        if (ObjUtil.isEmpty(jsonObject)) {
            throw new IllegalArgumentException("更新定时任务失败("+handler+")");
        }

        //(3).判断响应结果
        Integer code = jsonObject.getInteger("code");
        if (code != 200) {
            String msg = jsonObject.getString("msg");
            log.info("更新定时任务失败{},errorCode:{},msg:{}","("+handler+")",code,msg);
            throw new IllegalArgumentException(msg);
        }

        return code;
    }

    /**
     * 向xxl-job-admin删除定时任务
     * corn : corn
     */
    public static Integer removeJob(String xxlJobAdminAddress,String jobId) {
        //3.通过http post接口向xxl-job-admin删除定时任务
        //(1).请求头
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("Authorization","Basic YWRtaW46MTIzNDU2");

        //(2).请求参数
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("id",jobId);
        JSONObject jsonObject = HttpClientUtil.doPostFormHeader(xxlJobAdminAddress + REMOVE_INFO_URL, reqMap, headerMap);
        if (ObjUtil.isEmpty(jsonObject)) {
            throw new IllegalArgumentException("删除定时任务失败，jobId("+jobId+")");
        }
        //(3).判断响应结果
        Integer code = jsonObject.getInteger("code");
        if (code != 200) {
            String msg = jsonObject.getString("msg");
            log.info("创建定时任务失败jobId{},errorCode:{},msg:{}","("+jobId+")",code,msg);
            throw new IllegalArgumentException(msg);
        }
        return code;
    }


    /**
     * 通过appName获取到该appName对应的JobGroupNumber
     */
    private static Integer getJobGroupIdByAppName(String xxlJobAdminAddress,String appName){

        //1.封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put("appName", appName);
        String reqJsonStr = JSONUtil.toJsonStr(params);

        //2.封装请求头
        Map<String,Object> headerMap = new HashMap<>();
        headerMap.put("Authorization","Basic YWRtaW46MTIzNDU2");

        //3.执行请求
        JSONObject jsonObject = HttpClientUtil.doPostJsonHeader(xxlJobAdminAddress + GET_GROUP_ID, reqJsonStr,headerMap);
        if (ObjUtil.isEmpty(jsonObject)) {
            throw new IllegalArgumentException("获取任务组id失败");
        }
        Integer code = jsonObject.getInteger("code");
        if (code != 200) {
            String msg = jsonObject.getString("msg");
            log.info("获取任务组id失败,errorCode:{},msg:{}", code, msg);
            throw new IllegalArgumentException(msg);
        }
        Map<String, Object> map = (Map<String, Object>) jsonObject.get("content");
        Integer jobGroupId = (Integer) map.get("id");
        return jobGroupId;
    }

    /**
     * 构建XxlJobInfo
     */
    private static XxlJobInfo buildXxlJobInfo(String corn,String handler,String handlerParam,Integer jobGroupId,String jobDesc){
        XxlJobInfo xxlJobInfo = XxlJobInfo.builder()
                .jobGroup(jobGroupId)
                .jobDesc(jobDesc)
                .author("jingjia")
                .scheduleType("CRON")
                .scheduleConf(corn)
                .glueType("BEAN")
                .executorHandler(handler)
                .executorParam(handlerParam)
                .misfireStrategy("DO_NOTHING")
                .executorRouteStrategy("FIRST")
                .executorBlockStrategy("SERIAL_EXECUTION")
                .triggerStatus(1)
                .build();
        return xxlJobInfo;
    }

/*    public static void main(String[] args) {
        testDelete();
    }*/
    private static void testDelete(){
        XxlJobUtil.removeJob("http://192.168.2.230:9090/xxl-job-admin", "5");
    }

    private static void testAdd(){
        Long threeDay = 3*24*60*60*1000L;
        Long time = System.currentTimeMillis() + threeDay;
        String dateStr = DateUtil.timeStamp2dateStr(time);
        String corn = DateUtil.dateStrConvertCronExpression(dateStr, 30);

        System.out.println("时间:"+dateStr+",corn:"+corn);

        String jobId = XxlJobUtil.addJob("http://192.168.2.230:9090/xxl-job-admin", corn, "testmymy", "{'key':'123'}","测试","bid-service");

        System.out.println("testmymy:"+jobId);
    }
}
