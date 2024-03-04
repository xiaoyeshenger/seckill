package com.jsxa.vapp.sys.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.bo.vo.RegionVo;
import com.jsxa.vapp.sys.bo.dto.RegionPageReqDto;
import com.jsxa.vapp.sys.bo.dto.RegionReqDto;

import java.util.List;
import java.util.Map;

/*
 * @Author zhangyong
 * @Description //RegionService接口
 * @Date 2021/09/16 16:43
 * @Param
 * @return
 **/
public interface RegionService {


    //添加区域
    Map<String, Object> addRegion(RegionReqDto regionReqDto);

    //通过id删除区域
    Map<String, Object> deleteRegionById( Long id);

    //更新区域
    Map<String, Object> updateRegion(RegionReqDto regionReqDto);

    //通过id查询区域
    Map<String, Object> getRegionById(Long id);

    //查询所有区域列表并分页
    PageVo<Map<String, Object>> getRegionListPageVo(RegionPageReqDto regionPageReqDto);


    //通过code查询区域
    RegionVo getRegionByCode(String code);


    //通过父级地址码(parentCode)查询所有的子级地址列表
    RegionVo getAllChildListByParentCode(String parentCode);

    RegionVo getAssignChildListByParentCode(String parentCode);


    //通过父级地址码(parentCode)查询所有的子级地址列表
    RegionVo getChildListByParentCode(String parentCode);

    //通过子级地址码(childCode)查询所有的父级级地址列表
    List<Map<String, Object>> getParentListByChildCode(String childCode);

    //通过父级地址码(parentCode)查询所有的区市县地址列表
    List<Map<String, Object>> getCountyListByParentCode(String parentCode);

    Map<String, Object> getRegionVo();

}