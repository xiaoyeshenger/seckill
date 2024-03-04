package com.jsxa.vapp.common.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EasyExcelListener extends AnalysisEventListener<Map<Integer, String>> {
    //表头数据（存储所有的表头数据）
    private List<Map<Integer, String>> headList = new ArrayList<>();
    //数据体
    private List<Map<Integer, String>> dataList = new ArrayList<>();

    //这里会一行行的返回头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        //存储全部表头数据
        headList.add(headMap);
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        dataList.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

    public List<Map<Integer, String>> getHeadList() {
        return headList;
    }

    public List<Map<Integer, String>> getDataList() {
        return dataList;
    }

}