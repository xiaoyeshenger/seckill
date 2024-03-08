package com.jsxa.vapp.sys.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.*;
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
import java.util.*;


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
     * 处理海康/大华/宇视事件告警
     */
    @RequestMapping(value = "receiveEventData", produces = { "application/json" })
    public Map<String, Object> receiveHikEventData(HttpServletRequest request, MultipartHttpServletRequest multipartHttpServletRequest){

        //1.获取http事件数据
        Map<String, Object> reqParam = RequestHandleUtil.getReqParam(request);
        String reqStr = JSONUtils.toJSONString(reqParam);
        log.info("step1 ---> 请求路径:{},设备IP:{},方法类型:{}" ,request.getRequestURL(), request.getRemoteAddr(),request.getMethod());
        log.info("step2 ---> 请求内容:{}",reqStr);
        /*if(reqParam.isEmpty()){
            getMultipartFileList(multipartHttpServletRequest,"MoveDetection.xml","moveAddr");
        }*/

        //2.从配置文件中获取监听的事件类型列表
        //String[] eventTypeArr = {"facedetection","fielddetection","parking","smokeDetection","fireDetection","smokeAndFireDetection","regionEntrance","fielddetection","linedetection","safetyHelmetDetection","TMA","VMD"/*,"PTZFXYFinish"*/};
        String[] eventTypeArr = eventTypeList.split(",");

        //3.如果事件数据包含监听的事件类型则处理
        for (int i = 0; i < eventTypeArr.length; i++) {
            String eventType = eventTypeArr[i];
            if(reqStr.contains(eventType)){

                //(1).获取到xml字符串并转为map
                String xmlStr = (String)reqParam.get(eventType);
                Map<String, Object> stringObjectMap = XmlUtil.multilayerXmlToMap(xmlStr);
                Map<String, Object> eventNotificationAlertMap = (Map<String, Object>)stringObjectMap.get("EventNotificationAlert");

                //(2).获取到mac地址
                String macAddress = (String)eventNotificationAlertMap.get("macAddress");
                macAddress = macAddress.replaceAll(":", "-");

                //(3).查询数据库中是否有该mac地址对应的设备，不为空再继续处理
                if(true){

                    //--1.设备(通道)名称
                    String channelName = (String)eventNotificationAlertMap.get("channelName");

                    //--2.告警时间
                    String dateTime = (String)eventNotificationAlertMap.get("dateTime");
                    Long tvsec = DateUtil.getTimestampDateTime(dateTime) / 1000;

                    log.info("step3 ---> eventType:{},macAddress:{},channelName:{}",eventType,macAddress,channelName);

                    //--3.保存告警图片到文件服务器(minio)
                    List<MultipartFile> hikEventMultipartFileList = getHikEventMultipartFileList(multipartHttpServletRequest, eventType);
                    if(!ObjUtil.isEmpty(hikEventMultipartFileList)){
                        //a.构建文件存储桶名称
                        String bucketName = "thermography";
                        if(eventType.equals("linedetection") || eventType.equals("fielddetection")){
                            bucketName = "outofline";
                        }

                        //b.遍历存储图片
                        List<String> fileNameList = new ArrayList<>();
                        for (int j = 0; j < hikEventMultipartFileList.size(); j++) {
                            MultipartFile multipartFile = hikEventMultipartFileList.get(j);
                            String name = multipartFile.getOriginalFilename() + ".jpg";
                            Boolean upload = minioUtil.upload(bucketName, multipartFile, name);
                            if(upload){
                                fileNameList.add(name);
                            }
                        }
                        //c.告警图片列表
                        String imgListStr = String.join(",", fileNameList);

                        //d.构建告警数据
                        JSONObject jo = new JSONObject();
                        jo.put("chnlid","51011399701327100052");
                        jo.put("devname",channelName);
                        jo.put("gid","1");
                        jo.put("tvsec",tvsec);
                        jo.put("img",imgListStr);

                        //f.发送数据到kafka
                        if(eventType.equals("smokeDetection") || eventType.equals("fireDetection") || eventType.equals("smokeAndFireDetection")){
                            log.info("step4 ---> 发送烟火告警");
                        }

                        if(eventType.equals("linedetection") || eventType.equals("fielddetection")){
                            log.info("step5 ---> 发送越界告警");
                        }
                    }
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


    //@ApiIgnore("接收HTTP数据")
    //@RequestMapping(value = "receiveEventDataOld", produces = { "application/json" })
    public Map<String, Object> sendScanCarFormNew(HttpServletRequest request,MultipartHttpServletRequest multipartHttpServletRequest){

        //log.info("step1 ---> 请求的路径:{}，远程设备ip:{}，方法类型:{}" ,request.getRequestURL(), request.getRemoteAddr(),request.getMethod());
        //getMultipartFile(multipartHttpServletRequest,"MoveDetection.xml");
        Map<String, Object> reqParam = RequestHandleUtil.getReqParam(request);
        //Map<String, String[]> reqParam = convertFormData(request);
        String reqStr = JSONUtils.toJSONString(reqParam);

        String[] eventTypeArr = {"facedetection","fielddetection","parking","smokeDetection","fireDetection","smokeAndFireDetection","regionEntrance","fielddetection","linedetection","safetyHelmetDetection","TMA","VMD","PTZFXYFinish"};

        for (int i = 0; i < eventTypeArr.length; i++) {
            String eventType = eventTypeArr[i];
            if(reqStr.contains(eventType)){
                if(eventType.equals("PTZFXYFinish")){
                    String ptzfxyFinishStr = (String)reqParam.get("PTZFXYFinish");
                    JSONObject parse = JSONObject.parseObject(ptzfxyFinishStr);
                    String macAddress = parse.getString("macAddress");
                    macAddress = macAddress.replaceAll(":", "-");
                    JSONObject pTZFXYFinishJSONObject = parse.getJSONObject("PTZFXYFinish");
                    String presetName = pTZFXYFinishJSONObject.getString("presetName");
                    redisService.hmSet("hik_alarm_presetName",macAddress,presetName);
                }else{
                    String xmlStr = (String)reqParam.get(eventType);
                    Map<String, Object> stringObjectMap = XmlUtil.multilayerXmlToMap(xmlStr);
                    Map<String, Object> eventNotificationAlertMap = (Map<String, Object>)stringObjectMap.get("EventNotificationAlert");
                    if(eventType.equals("TMA")){
                        Map<String, Object> detectionRegionListMap = (Map<String, Object>)eventNotificationAlertMap.get("DetectionRegionList");
                        Map<String, Object> detectionRegionEntryMap = (Map<String, Object>)detectionRegionListMap.get("DetectionRegionEntry");
                        Map<String, Object> tmaMap = (Map<String, Object>)detectionRegionEntryMap.get("TMA");
                        String currTemperatureStr = (String)tmaMap.get("currTemperature");
                        Double currTemperature = Double.valueOf(currTemperatureStr);
                        if(currTemperature < 50.0){
                            break;
                        }
                    }
                    String macAddress = (String)eventNotificationAlertMap.get("macAddress");
                    macAddress = macAddress.replaceAll(":", "-");
                    String channelName = (String)eventNotificationAlertMap.get("channelName");
                    log.info("eventType:{},macAddress:{},channelName:{}",eventType,macAddress,channelName);
                    String address = channelName +"("+macAddress+")";

                    String hikAlarmPresetName = (String)redisService.hmGet("hik_alarm_presetName", macAddress);
                    if(!ObjUtil.isEmpty(hikAlarmPresetName)){
                        address = address+"_"+hikAlarmPresetName;
                    }

                    //保存图片
                    if(macAddress.equals("a4-4c-62-26-bf-2a")){
                        getMultipartFileList(multipartHttpServletRequest,eventType,address);
                    }
                }
            }
        }


        Map<String, Object> map = new HashMap<>();
        map.put("data",reqParam);
        return map;
    }

    public static void getMultipartFileList(MultipartHttpServletRequest multipartHttpServletRequest,String fileKey,String address) {
        if(fileKey.equals("fielddetection")){
            fileKey="intrusionImage";
        }

        if(fileKey.equals("linedetection")){
            fileKey="lineCrossImage";
        }


        List<MultipartFile> fileList = multipartHttpServletRequest.getFiles(fileKey);
        if(!ObjUtil.isEmpty(fileList)){
            for (int i = 0; i < fileList.size(); i++) {
                MultipartFile multipartFile = fileList.get(i);
                if (multipartFile != null) {
                    String name = multipartFile.getName();
                    String originalFilename = multipartFile.getOriginalFilename();
                    System.out.println("name:"+name);
                    System.out.println("originalFilename:"+originalFilename);

                    String fileUploadPathPrefix = "E:/staticResource/";
                    String photoFileUploadPath = fileUploadPathPrefix + "hikEventImage/";
                    try {
                        String fileName = address+"_"+DateUtil.getCurTimeStr()+"_"+fileKey+"_"+(i+1)+"_"+multipartFile.getName()+".jpg";
                        if(fileKey.contains(".xml")){
                            fileName = address+"_"+DateUtil.getCurTimeStr()+"_"+fileKey+"_"+(i+1)+"_"+multipartFile.getName();
                        }

                        FileUploadUtils.uploadFile(multipartFile, photoFileUploadPath, fileName);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    public static Map<String, String[]> convertFormData(HttpServletRequest request) {
        // 创建一个空的HashMap作为结果集合
        HashMap<String, String[]> result = new HashMap<>();

        // 遍历所有的表单字段名称
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

            // 根据字段名称获取相应的参数值
            String[] values = request.getParameterValues(paramName);

            // 将参数名和参数值添加到结果集合中
            result.put(paramName, values);
        }

        return result;
    }
}