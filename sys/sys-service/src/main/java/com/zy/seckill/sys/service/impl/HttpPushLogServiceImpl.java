package com.zy.seckill.sys.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.zy.seckill.common.bo.po.SysFile;
import com.zy.seckill.common.bo.vo.RegionVo;
import com.zy.seckill.common.dao.HttpPushLogDao;
import com.zy.seckill.common.bo.po.HttpPushLog;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.bo.dto.HttpPushLogPageReqDto;
import com.zy.seckill.common.mapper.SysFileDynamicSqlSupport;
import com.zy.seckill.common.mapper.SysFileMapper;
import com.zy.seckill.common.redis.RedisService;
import com.zy.seckill.common.utils.*;
import com.zy.seckill.sys.bo.dto.HttpPushLogReqDto;
import com.zy.seckill.sys.bo.vo.DeviceVo;
import com.zy.seckill.sys.bo.vo.ProductVo;
import com.zy.seckill.sys.excel.HttpPushLogCdlotExcelVo;
import com.zy.seckill.sys.excel.HttpPushLogExcelVo;
import com.zy.seckill.sys.service.HttpPushLogService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;


/*
 * @Author wangchao
 * @Description //HttpPushLogService接口实现类
 * @Date 2023/03/23 11:02
 * @Param
 * @return
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class HttpPushLogServiceImpl implements HttpPushLogService {

    public static final String SendDeviceLog = "http://221.237.108.214:8001/api-devicelog-collect/data/NonLoraData";

    private final CacheUtil cacheUtil;

    private final MinioUtil minioUtil;

    private final RedisService redisService;

    private final HttpPushLogDao httpPushLogDao;

    private final SysFileMapper sysFileMapper;


    @Override
    public Map<String, Object> getHttpPushLogById(String id) {
        HttpPushLog e = httpPushLogDao.getHttpPushLogById(id);
        if (e == null) {
            throw new IllegalArgumentException("id为:" + id + "的HTTP推送日志信息不存在");
        }
        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("pushType", e.getPushType());
        attr.put("operateTime", e.getOperateTime());
        attr.put("operateDate", e.getOperateDate());
        attr.put("status", e.getStatus());
        attr.put("latestData", e.getLatestData());
        attr.put("httpReqUrl", e.getHttpReqUrl());
        attr.put("httpReqHeader", e.getHttpReqHeader());
        attr.put("httpReqParam", e.getHttpReqParam());
        attr.put("httpResult", e.getHttpResult());
        attr.put("errorMsg", e.getErrorMsg());
        attr.put("deviceSerialNum", e.getKeyWord());
        attr.put("deviceUuid", e.getDeviceUuid());
        attr.put("productId", e.getProductId());
        attr.put("productName", e.getProductName());
        attr.put("productType", e.getProductType());
        attr.put("regionCode", e.getRegionCode());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getHttpPushLogListPageVo(HttpPushLogPageReqDto httpPushLogPageReqDto) {


        return PageVo.by(
                httpPushLogDao.getHttpPushLogListPageVo(httpPushLogPageReqDto),
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("pushType", e.getPushType());
                    attr.put("operateTime", e.getOperateTime());
                    attr.put("operateDate", e.getOperateDate());
                    attr.put("status", e.getStatus());
                    attr.put("latestData", e.getLatestData());
                    attr.put("httpReqUrl", e.getHttpReqUrl());
                    attr.put("httpReqHeader", e.getHttpReqHeader());
                    attr.put("httpReqParam", e.getHttpReqParam());
                    attr.put("httpResult", e.getHttpResult());
                    attr.put("errorMsg", e.getErrorMsg());
                    attr.put("deviceSerialNum", e.getKeyWord());
                    attr.put("deviceUuid", e.getDeviceUuid());
                    attr.put("productType", e.getProductType());
                    attr.put("productId", e.getProductId());
                    attr.put("productName", e.getProductName());
                    attr.put("regionCode", e.getRegionCode());
                    return attr;
                }
        );
    }

    @Override
    public void exportToExcel(HttpPushLogPageReqDto httpPushLogPageReqDto, HttpServletResponse response) {

        //1.查询列表
        List<HttpPushLog> httpPushLogList = httpPushLogDao.getHttpPushLogList(httpPushLogPageReqDto);

        //2.列表转为导出对象列表
        int order = 1;
        List<HttpPushLogExcelVo> httpPushLogExcelVoList = new ArrayList<>();
        for (int i = 0; i < httpPushLogList.size(); i++) {
            HttpPushLog httpPushLog = httpPushLogList.get(i);
            HttpPushLogExcelVo httpPushLogExcelVo = VoPoConverterUtil.copyProperties(httpPushLog, HttpPushLogExcelVo.class);
            httpPushLogExcelVo.setOrder(order++);
            httpPushLogExcelVo.setStatusCn(httpPushLog.getStatus() == 1 ? "成功" : "失败");
            httpPushLogExcelVo.setLatestDataCn(httpPushLog.getLatestData() == 1 ? "是" : "否");
            httpPushLogExcelVo.setPushTypeCn(cacheUtil.getDataDictName(httpPushLog.getPushType()));
            httpPushLogExcelVoList.add(httpPushLogExcelVo);
        }

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "DeviceInfoHttpRecord";
            String fileName = sheetName.concat("(" + DateUtil.getCurTimeStr() + ")").concat(".xlsx");

            //(2).response输出文件流格式
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //(3).获取到输出流
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.将输出流写入到response,直接响应给浏览器
        EasyExcel.write(outputStream)
                .head(HttpPushLogExcelVo.class)
                .sheet("Sheet1")
                .doWrite(httpPushLogExcelVoList);
    }

    @Override
    public void exportToCdlotExcel(HttpPushLogPageReqDto httpPushLogPageReqDto, HttpServletResponse response) {
        //1.查询列表
        List<HttpPushLog> httpPushLogList = httpPushLogDao.getHttpPushLogList(httpPushLogPageReqDto);

        //2.列表转为导出对象列表
        int order = 1;
        List<HttpPushLogCdlotExcelVo> httpPushLogCdlotExcelVoList = new ArrayList<>();
        for (int i = 0; i < httpPushLogList.size(); i++) {
            //--1.获取到当HttpPushLog的设备序列号
            HttpPushLog httpPushLog = httpPushLogList.get(i);
            String serialNumber = httpPushLog.getKeyWord();
            //--2.获取到设备信息
            DeviceVo deviceVo = JSONObject.parseObject((String) redisService.hmGet("device", serialNumber), DeviceVo.class);
            if (!ObjUtil.isEmpty(deviceVo)) {
                //--3.构建httpPushLogCdlotExcelVo

                //产品信息
                ProductVo productVo = JSONObject.parseObject((String) redisService.hmGet("product", String.valueOf(deviceVo.getProductId())), ProductVo.class);
                HttpPushLogCdlotExcelVo httpPushLogCdlotExcelVo = HttpPushLogCdlotExcelVo.builder().order(order++)
                        .deviceShowName(deviceVo.getName())
                        .deviceName(productVo.getName())
                        .deviceUuid(deviceVo.getUuid())
                        .deviceSerial(deviceVo.getSerialNumber())
                        .manufacturer("四川金石信安科技有限公司")
                        .deviceModel(productVo.getProductModel())
                        .deviceType(productVo.getName())
                        .belongGrid(ObjUtil.isEmpty(deviceVo.getGridId()) ? null : (String) redisService.hmGet("gridName", String.valueOf(deviceVo.getGridId())))
                        .address(deviceVo.getAddress())
                        .useObject(null)
                        .longitude(convert6Digit(GPSUtil.bd09_To_gps84(Double.valueOf(deviceVo.getLatitude()), Double.valueOf(deviceVo.getLongitude()))[1]))
                        .latitude(convert6Digit(GPSUtil.bd09_To_gps84(Double.valueOf(deviceVo.getLatitude()), Double.valueOf(deviceVo.getLongitude()))[0]))
                        .altitude(null)
                        .maintenanceUnit("四川金石信安科技有限公司")
                        .contactPerson("阴琴")
                        .contactMobile("18140222267")
                        .switchInCdlot(redisService.hmHasKey("devicePushStatus", serialNumber) ? "是" : null)
                        .remark(null)
                        .build();
                httpPushLogCdlotExcelVoList.add(httpPushLogCdlotExcelVo);
            }

        }

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "CdlotDeviceInfoHttpRecord";
            String fileName = sheetName.concat("(" + DateUtil.getCurTimeStr() + ")").concat(".xlsx");

            //(2).response输出文件流格式
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //(3).获取到输出流
            outputStream = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.将输出流写入到response,直接响应给浏览器
        //--1.获取到模板文件
        SysFile sysFile = sysFileMapper.selectByExampleOne()
                .where(SysFileDynamicSqlSupport.fileKey, isEqualTo("lotDeviceTempExcel"))
                .build()
                .execute();
        if (ObjUtil.isEmpty(sysFile)) {
            throw new IllegalArgumentException("lotDevice模板文件暂未上传,请联系管理员");
        }
        InputStream fileInputStream = minioUtil.getFileInputStream(sysFile.getStorePath(), sysFile.getFileName());

        //2.将内容写入模板文件并响应到浏览器
        EasyExcel.write(outputStream)
                .withTemplate(fileInputStream)
                .head(HttpPushLogCdlotExcelVo.class)
                .sheet("对接设备列表")
                .doWrite(httpPushLogCdlotExcelVoList);
    }

    private String convert6Digit(Double num) {
        String result = "";

        String numStr = String.valueOf(num);
        String preStr = numStr.substring(0, numStr.lastIndexOf("."));
        String substring = numStr.substring(numStr.lastIndexOf("."));

        int length = substring.length();
        if (length == 7) {
            result = substring;
        }

        if (length > 7) {
            result = substring.substring(0, 7);
        }
        if (length < 7) {
            int digit = 7 - length;
            String zeroDigit = "";
            for (int i = 0; i < digit; i++) {
                zeroDigit += "0";
            }
            result = substring.concat(zeroDigit);
        }

        return preStr + result;
    }


    @Override
    public Map<String, Object> againPushLog(HttpPushLogReqDto httpPushLogReqDto) {
        Map<String, Object> result = new HashMap<>();
        String id = httpPushLogReqDto.getId();
        String dataJson = httpPushLogReqDto.getDataJson();
        String headerJson = httpPushLogReqDto.getHeaderJson();
        JSONObject heardJsonObject = JSONObject.parseObject(headerJson);
        JSONObject dataJsonObject = JSONObject.parseObject(dataJson);

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("Authorization", heardJsonObject.getString("Authorization"));
        JSONObject jsonObject = HttpClientUtil.doPostJsonHeader(SendDeviceLog, dataJson, headerMap);
        String resultStr = ObjUtil.isEmpty(jsonObject) ? "" : JSONObject.toJSONString(jsonObject);

        HttpPushLog httpPushLog = httpPushLogDao.getHttpPushLogById(id);

        byte status = (byte) (CommonUtils.isEmpty(resultStr) ? 0 : jsonObject.getInteger("code") == 200 ? 1 : 0);

        HttpPushLog againPushLog = VoPoConverterUtil.copyProperties(httpPushLog, HttpPushLog.class);
        againPushLog.setHttpReqHeader(headerJson)
                .setHttpReqParam(dataJson)
                .setHttpResult(resultStr)
                .setLatestData((byte) 1)
                .setStatus(status)
                .setOperateTime(System.currentTimeMillis())
                .setOperateDate(DateUtil.getCurTimeString());

        httpPushLogDao.insert(againPushLog);

        result.put("msg", status == 1 ? "推送成功" : "推送失败");
        result.put("serviceSuccess", status == 1);
        return result;
    }
}