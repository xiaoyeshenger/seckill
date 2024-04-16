package com.zy.seckill.sys.service;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.ParamSettingReqDto;
import com.zy.seckill.sys.bo.dto.ParamSettingPageReqDto;
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