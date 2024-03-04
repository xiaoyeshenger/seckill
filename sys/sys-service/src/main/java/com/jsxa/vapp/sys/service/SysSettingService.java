package com.jsxa.vapp.sys.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.sys.bo.dto.SysSettingReqDto;
import com.jsxa.vapp.sys.bo.dto.SysSettingPageReqDto;
import java.util.Map;

/*
 * @Author zhangyong
 * @Description //SysSettingService接口
 * @Date 2022/03/09 09:38
 * @Param
 * @return
 **/
public interface SysSettingService {


    //添加系统设置
    Map<String, Object> addSysSetting(Map<String, Object> headerMap,SysSettingReqDto sysSettingReqDto);

    //通过id删除系统设置
    Map<String, Object> deleteSysSettingById(Map<String, Object> headerMap,Long id);

    //更新系统设置
    Map<String, Object> updateSysSetting(Map<String, Object> headerMap,SysSettingReqDto sysSettingReqDto);

    //通过id查询系统设置
    Map<String, Object> getSysSettingById(Map<String, Object> headerMap,Long id);

    //查询所有系统设置列表并分页
    PageVo<Map<String, Object>> getSysSettingListPageVo(Map<String, Object> headerMap,SysSettingPageReqDto sysSettingPageReqDto);
}