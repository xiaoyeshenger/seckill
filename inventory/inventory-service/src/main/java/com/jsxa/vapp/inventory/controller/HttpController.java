package com.jsxa.vapp.inventory.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.jsxa.vapp.common.annotation.Log;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.enums.BusinessType;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.*;
import com.jsxa.vapp.common.valid.ValidationGroup;
import com.jsxa.vapp.common.valid.ValidationGroup.ValidationUpdate;
import com.jsxa.vapp.inventory.bo.dto.OrganizationPageReqDto;
import com.jsxa.vapp.inventory.bo.dto.OrganizationReqDto;
import com.jsxa.vapp.inventory.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


    @Resource
    private RedisService redisService;


    @ApiIgnore("接收HTTP数据")
    @RequestMapping(value = "receiveEventData", produces = { "application/json" })
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
                    getMultipartFileList(multipartHttpServletRequest,eventType,address);
                }
            }
        }

        Map<String, Object> headerAsMap = HttpRequestReaderUtil.getHeaderAsMap(request);
        //log.info("step2 ---> 请求的header:{}",headerAsMap.toString());
        //log.info("step3 ---> 请求的param:{}",reqStr);

        if(ObjUtil.isEmpty(reqStr)){
            throw new IllegalArgumentException("接收到的数据为空");
        }
        //JSONObject parse = (JSONObject) JSONObject.parse(reqStr);
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
                    String fileUploadPathPrefix = "E:/staticResource/";
                    String photoFileUploadPath = fileUploadPathPrefix + "hikEventImage/";
                    try {
                        FileUploadUtils.uploadFile(multipartFile, photoFileUploadPath, address+"_"+DateUtil.getCurTimeStr()+"_"+fileKey+"_"+(i+1)+"_"+multipartFile.getName()+".jpg");
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