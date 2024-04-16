package com.zy.seckill.sys.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.common.utils.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;


/**
 * @Author zhangyong
 * @Description OrganizationController类
 * @Date 2024/02/02 15:01
 * @Param
 * @return
 */
@RestController
@RequestMapping("/inventory/http")
@Api(tags = "测试接收告警事件数据")
@Validated
@RequiredArgsConstructor
@Slf4j
public class HttpController {

    @Value("${sys.dw.charset: UTF-8}")
    private String charset;

    @Value("${hik.event.typeList}")
    private String eventTypeList;

    @Resource
    private RedisService redisService;

    @Resource
    private MinioUtil minioUtil;

    /**
     * 接收HTTP请求数据
     */
    @RequestMapping(value = "receiveDjData", produces = { "application/json" })
    public Map<String, Object> receiveDjData(HttpServletRequest request) {
        //1.从request获取请求参数
        Map<String, Object> reqParam = RequestHandleUtil.getReqParam(request);
        String reqStr = JSONUtils.toJSONString(reqParam);
        log.info("step1 ---> 设备IP:{},方法类型:{},数据类型:{},请求内容:{}",request.getRemoteAddr(),request.getMethod(),request.getContentType(),reqStr);

        Map<String, Object> map = new HashMap<>();
        map.put("data",reqStr);
        return map;
    }

    /**
     * 处理海康/大华/宇视事件告警
     */
    @RequestMapping(value = "receiveEventDataHik", produces = { "application/json" })
    public Map<String, Object> receiveEventDataHik(HttpServletRequest request){


        //1.从request获取请求参数
        Map<String, Object> reqParam = RequestHandleUtil.getReqParam(request);
        String reqStr = JSONUtils.toJSONString(reqParam);
        log.info("step1 ---> 设备IP:{},方法类型:{},数据类型:{},请求内容:{}",request.getRemoteAddr(),request.getMethod(),request.getContentType(),reqStr);
        //2.判断请求参数是否为空,为空查看是否有文件上传，不为空则处理参数
        //(1).参数为空,查看是否有文件上传
        if(ObjUtil.isEmpty(reqParam)) {
            if (request instanceof MultipartHttpServletRequest) {
                //--1.HttpServletRequest转为MultipartHttpServletRequest
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                //--2.获取所有的文件名
                minioUtil.uploadMultipartFile(multipartHttpServletRequest,"eventdatafile");
            }
        }else {
            //(2).参数不为空,处理参数
            //1).从配置文件中获取监听的事件类型列表
            String[] eventTypeArr = eventTypeList.split(",");

            //2).如果事件数据包含监听的事件类型则处理
            for (int i = 0; i < eventTypeArr.length; i++) {
                String eventType = eventTypeArr[i];
                if(reqStr.contains(eventType)){
                    //--1.获取到xml字符串并转为map(内容类型为xml，则需要从map中取出xml字符串，key为xml是因为RequestHandleUtil.doPost()自定义的)
                    String contentType = request.getContentType();
                    String xmlStr;
                    if(contentType.contains("xml")){
                        xmlStr = (String)reqParam.get("xml");
                    }else {
                        xmlStr = (String)reqParam.get(eventType);
                    }
                    Map<String, Object> stringObjectMap = XmlUtil.multilayerXmlToMap(xmlStr);
                    Map<String, Object> eventNotificationAlertMap = (Map<String, Object>)stringObjectMap.get("EventNotificationAlert");



                    //--2.获取到mac地址
                    String macAddress = (String)eventNotificationAlertMap.get("macAddress");

                    //--3.查询数据库中是否有该mac地址对应的设备，不为空再继续处理
                    /*DeviceMacSerial deviceMacSerial = deviceMacSerialMapper.selectByExampleOne()
                            .where(DeviceMacSerialDynamicSqlSupport.macAddr, isEqualTo(macAddress))
                            .build()
                            .execute();*/
                    //if(!ObjUtil.isEmpty(deviceMacSerial)){
                    if(true){
                        //a.通道(设备)名称
                        String channelName = (String)eventNotificationAlertMap.get("channelName");

                        //b.告警时间
                        String dateTime = (String)eventNotificationAlertMap.get("dateTime");
                        Long tvsec = DateUtil.getTimestampDateTime(dateTime) / 1000;

                        //c.如果请求中有文件上传则解析文件保存告警图片到文件服务器(minio)
                        if (request instanceof MultipartHttpServletRequest) {
                            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                            //--1.构建文件存储桶名称
                            String bucketName = "thermography";
                            if(eventType.equals("linedetection") || eventType.equals("fielddetection")){
                                bucketName = "outofline";
                            }
                            //--2.上传文件
                            List<String> fileNameList = minioUtil.uploadMultipartFile(multipartHttpServletRequest, bucketName);

                            //--3.文件列表不为空，推送消息到kafka
                            if(!ObjUtil.isEmpty(fileNameList)){
                                String imgListStr = String.join(",", fileNameList);
                                JSONObject jo = new JSONObject();
                                jo.put("chnlid","51011399701327100052");
                                jo.put("devname",channelName);
                                jo.put("gid","1");
                                jo.put("tvsec",tvsec);
                                jo.put("img",imgListStr);
                                jo.put("source","hikHttpPush");

                                if(eventType.equals("smokeDetection") || eventType.equals("fireDetection") || eventType.equals("smokeAndFireDetection")){
                                    log.info("step2 ---> 发送烟火告警到kafka eventType:{},macAddress:{},channelName:{}",eventType,macAddress,channelName);
                                    //kafkaConsumerService.processKafka(DeviceDataType.thermography, jo,deviceMacSerial.getSerialNum());
                                }

                                if(eventType.equals("linedetection") || eventType.equals("fielddetection")){
                                    log.info("step3 ---> 发送越界告警到kafka eventType:{},macAddress:{},channelName:{}",eventType,macAddress,channelName);
                                    //kafkaConsumerService.processKafka(DeviceDataType.outOfLine, jo,deviceMacSerial.getSerialNum());
                                }
                            }
                        }
                    }
                    //--4.事件每次都只发一种类型，匹配到一个类型，即结束当前循环
                    break;
                }
            }
        }


        Map<String, Object> map = new HashMap<>();
        map.put("data",reqParam);
        return map;
    }



    /**
     * 获取海康事件告警图片列表
     */
    public static List<MultipartFile> getHikEventMultipartFileList(MultipartHttpServletRequest multipartHttpServletRequest,String fileKey) {
        if(fileKey.equals("fielddetection")){
            fileKey="intrusionImage";
        }
        if(fileKey.equals("linedetection")){
            fileKey="lineCrossImage";
        }
        List<MultipartFile> fileList = multipartHttpServletRequest.getFiles(fileKey);
        return fileList;
    }
}