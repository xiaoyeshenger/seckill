package com.zy.seckill.sys.service;


import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.DataDictPageReqDto;
import com.zy.seckill.sys.bo.dto.DataDictReqDto;

import java.util.Map;


public interface DataDictService {

    //通过value获取数据字典
    Map<String, Object> getDataDictTreeById(Long id);

    //获取所有的数据字典
    Map<String, Object> getDataDict();

    //增加
    Map<String, Object> addDataDict(DataDictReqDto dataDictReqDto);

    //删除
    Map<String, Object> deleteDataDictById(Long id);

    //修改
    Map<String, Object> updateDataDict(DataDictReqDto dataDictReqDto);

    //通过Id查询
    Map<String, Object> getDataDictById(Long id);

    PageVo<Map<String, Object>> getDataDictListPageVo(String userInfo, DataDictPageReqDto dataDictPageReqDto);
}
