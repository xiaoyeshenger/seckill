package com.jsxa.vapp.sys.service.impl;

import com.alibaba.excel.EasyExcel;
import com.jsxa.vapp.common.bo.dto.LoginLogPageReqDto;
import com.jsxa.vapp.common.bo.dto.OperateLogPageReqDto;
import com.jsxa.vapp.common.bo.po.LoginLog;
import com.jsxa.vapp.common.bo.po.OperateLog;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.dao.LoginLogDao;
import com.jsxa.vapp.common.utils.CacheUtil;
import com.jsxa.vapp.common.utils.DateUtil;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.sys.excel.LoginLogExcelVo;
import com.jsxa.vapp.sys.excel.OperateLogExcelVo;
import com.jsxa.vapp.sys.service.LoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import  com.jsxa.vapp.common.utils.VoPoConverterUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;


/*
 * @Author zhangyong
 * @Description //LoginLogService接口实现类
 * @Date 2022/03/02 16:01
 * @Param
 * @return
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {


    private final LoginLogDao loginLogDao;


    @Override
    @Transactional
    public Map<String, Object> deleteLoginLogById(Map<String, Object> headerMap,String id){

        loginLogDao.deleteById(id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除登录日志成功");
        return resultMap;
    }


    @Override
    public Map<String, Object> getLoginLogById(Map<String, Object> headerMap,String id){
        LoginLog e = loginLogDao.getLoginLogById(id);
        if(e == null){
            throw new IllegalArgumentException("id为:"+id+"的登录日志信息不存在");
        }
        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("userName", e.getUserName());
        attr.put("ip", e.getIp());
        attr.put("location", e.getLocation());
        attr.put("browser", e.getBrowser());
        attr.put("msg", e.getMsg());
        attr.put("os", e.getOs());
        attr.put("status", e.getStatus());
        attr.put("loginTime", e.getLoginTime());
        attr.put("regionCode", e.getRegionCode());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getLoginLogListPageVo(Map<String, Object> headerMap,LoginLogPageReqDto loginLogPageReqDto){

        return PageVo.by(
                        loginLogDao.getLoginLogListPageVo(loginLogPageReqDto),
                        e -> {
                            Map<String, Object> attr = new HashMap<>();
                            attr.put("id", e.getId());
                            attr.put("userName", e.getUserName());
                            attr.put("ip", e.getIp());
                            attr.put("location", e.getLocation());
                            attr.put("browser", e.getBrowser());
                            attr.put("msg", e.getMsg());
                            attr.put("os", e.getOs());
                            attr.put("status", e.getStatus());
                            attr.put("loginTime", e.getLoginTime());
                            attr.put("regionCode", e.getRegionCode());
                            return attr;
                        }
                );
        }

    @Override
    public void exportToExcel(LoginLogPageReqDto loginLogPageReqDto, HttpServletResponse response) {
        //1.查询到列表
        List<LoginLog> loginLogList = loginLogDao.getLoginLogList(loginLogPageReqDto);

        //2.列表转为导出对象列表
        int order = 1;
        List<LoginLogExcelVo> loginLogExcelVoList = new ArrayList<>();
        for (int i = 0; i <loginLogList.size() ; i++) {
            LoginLog loginLog = loginLogList.get(i);
            LoginLogExcelVo loginLogExcelVo = VoPoConverterUtil.copyProperties(loginLog, LoginLogExcelVo.class);
            loginLogExcelVo.setOrder(order++);
            loginLogExcelVo.setStatusCn(loginLog.getStatus()==1?"成功":"失败");
            loginLogExcelVoList.add(loginLogExcelVo);
        }

        //3.获取到输出流
        OutputStream outputStream = null;
        try {
            //(1).文件名，表名，表头，格式
            String sheetName = "登录日志(";
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
                .head(LoginLogExcelVo.class)
                .sheet("Sheet1")
                .doWrite(loginLogExcelVoList);
    }
}