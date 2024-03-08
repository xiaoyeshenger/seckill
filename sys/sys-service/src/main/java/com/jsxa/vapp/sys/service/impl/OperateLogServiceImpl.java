package com.jsxa.vapp.sys.service.impl;

import com.alibaba.excel.EasyExcel;
import com.jsxa.vapp.common.bo.dto.OperateLogPageReqDto;
import com.jsxa.vapp.common.bo.po.OperateLog;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.dao.OperateLogDao;
import com.jsxa.vapp.common.utils.CacheUtil;
import com.jsxa.vapp.common.utils.DateUtil;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.common.utils.VoPoConverterUtil;
import com.jsxa.vapp.sys.excel.DeviceInfoPushExcelVo;
import com.jsxa.vapp.sys.excel.OperateLogExcelVo;
import com.jsxa.vapp.sys.service.OperateLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;


/*
 * @Author zhangyong
 * @Description //OperateLogService接口实现类
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class OperateLogServiceImpl implements OperateLogService {


    private final OperateLogDao operateLogDao;


    @Override
    @Transactional
    public Map<String, Object> deleteOperateLogById(Map<String, Object> headerMap,String id){
        operateLogDao.deleteById(id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除操作日志成功");
        return resultMap;
    }


    @Override
    public Map<String, Object> getOperateLogById(Map<String, Object> headerMap,String id){
        OperateLog e = operateLogDao.getOperateLogById(id);
        if(e == null){
            throw new IllegalArgumentException("id为:"+id+"的操作日志信息不存在");
        }
        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("moduleName", e.getModuleName());
        attr.put("businessType", e.getBusinessType());
        attr.put("methodName", e.getMethodName());
        attr.put("methodCnName", e.getMethodCnName());
        attr.put("requestMethod", e.getRequestMethod());
        attr.put("reqParam", e.getReqParam());
        attr.put("respResult", e.getRespResult());
        attr.put("type", e.getType());
        attr.put("url", e.getUrl());
        attr.put("ip", e.getIp());
        attr.put("location", e.getLocation());
        attr.put("status", e.getStatus());
        attr.put("time", e.getOperateTime());
        attr.put("errorMsg", e.getErrorMsg());
        attr.put("operatorName", e.getOperatorName());
        attr.put("operatorMobile", e.getOperatorMobile());
        attr.put("regionCode", e.getRegionCode());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getOperateLogListPageVo(Map<String, Object> headerMap,OperateLogPageReqDto operateLogPageReqDto){
        return PageVo.by(
                        operateLogDao.getOperateLogListPageVo(operateLogPageReqDto),
                        e -> {
                            Map<String, Object> attr = new HashMap<>();
                            attr.put("id", e.getId());
                            attr.put("moduleName", e.getModuleName());
                            attr.put("businessType", e.getBusinessType());
                            attr.put("methodName", e.getMethodName());
                            attr.put("methodCnName", e.getMethodCnName());
                            attr.put("requestMethod", e.getRequestMethod());
                            attr.put("reqParam", e.getReqParam());
                            attr.put("type", e.getType());
                            attr.put("url", e.getUrl());
                            attr.put("ip", e.getIp());
                            attr.put("location", e.getLocation());
                            attr.put("status", e.getStatus());
                            attr.put("time", e.getOperateTime());
                            attr.put("errorMsg", e.getErrorMsg());
                            attr.put("operatorName", e.getOperatorName());
                            attr.put("operatorMobile", e.getOperatorMobile());
                            attr.put("regionCode", e.getRegionCode());
                            return attr;
                        }
                );
        }

    @Override
    public void exportToExcel(OperateLogPageReqDto operateLogPageReqDto, HttpServletResponse response) {
        //1.查询到列表
        List<OperateLog> operateLogList = operateLogDao.getOperateLogList(operateLogPageReqDto);

        //2.列表转为导出对象列表
        int order = 1;
        List<OperateLogExcelVo> operateLogExcelVoList = new ArrayList<>();
        for (int i = 0; i <operateLogList.size() ; i++) {
            OperateLog operateLog = operateLogList.get(i);
            OperateLogExcelVo operateLogExcelVo = VoPoConverterUtil.copyProperties(operateLog, OperateLogExcelVo.class);
            operateLogExcelVo.setOrder(order++);
            operateLogExcelVo.setStatusCn(operateLog.getStatus()==1?"成功":"失败");
            operateLogExcelVoList.add(operateLogExcelVo);
        }

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "操作日志(";
            String fileName = sheetName.concat(DateUtil.getCurTimeStr()+")").concat(".xlsx");

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
                .head(OperateLogExcelVo.class)
                .sheet("Sheet1")
                .doWrite(operateLogExcelVoList);
    }
}