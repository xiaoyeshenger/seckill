package com.jsxa.vapp.sys.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.sys.bo.dto.ParamSettingReqDto;
import com.jsxa.vapp.sys.bo.dto.ParamSettingPageReqDto;
import java.util.Map;

/*
 * @Author zhangyong
 * @Description //ParamSettingService接口
 * @Date 2022/03/22 14:08
 * @Param
 * @return
 **/
public interface ParamSettingService {


    //添加参数设置
    Map<String, Object> addParamSetting(Map<String, Object> headerMap,ParamSettingReqDto paramSettingReqDto);

    //通过id删除参数设置
    Map<String, Object> deleteParamSettingById(Map<String, Object> headerMap,Long id);

    //更新参数设置
    Map<String, Object> updateParamSetting(Map<String, Object> headerMap,ParamSettingReqDto paramSettingReqDto);

    //通过id查询参数设置
    Map<String, Object> getParamSettingById(Map<String, Object> headerMap,Long id);

    //查询所有参数设置列表并分页
    PageVo<Map<String, Object>> getParamSettingListPageVo(Map<String, Object> headerMap,ParamSettingPageReqDto paramSettingPageReqDto);
}