package com.jsxa.vapp.common.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.util.CollectionUtils;
import com.alibaba.excel.util.IoUtils;
import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EasyExcelImportUtils {


    /**
     * 动态获取全部列和数据体
     * [stream, parseRowNumber]
     *
     * @return {@link List< Map< String, String>>}
     * @throws
     */
    public static List<Map<String, String>> parseExcelToData(byte[] stream, Integer parseRowNumber) {
        EasyExcelListener readListener = new EasyExcelListener();
        EasyExcelFactory.read(new ByteArrayInputStream(stream)).registerReadListener(readListener).headRowNumber(parseRowNumber).sheet(0).doRead();
        List<Map<Integer, String>> headList = readListener.getHeadList();
        if (CollectionUtils.isEmpty(headList)) {
            throw new RuntimeException("Excel表头不能为空");
        }
        List<Map<Integer, String>> dataList = readListener.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            throw new RuntimeException("Excel数据内容不能为空");
        }
        //获取头部,取最后一次解析的列头数据
        Map<Integer, String> excelHeadIdxNameMap = headList.get(headList.size() - 1);
        //封装数据体
        List<Map<String, String>> excelDataList = Lists.newArrayList();
        for (Map<Integer, String> dataRow : dataList) {
            Map<String, String> rowData = new LinkedHashMap<>();
            excelHeadIdxNameMap.entrySet().forEach(columnHead -> {
                rowData.put(columnHead.getValue(), dataRow.get(columnHead.getKey()));
            });
            excelDataList.add(rowData);
        }
        return excelDataList;
    }

    /**
     * 返回导入的所有数据
     * [file]
     *
     * @return {@link List< Map< String, String>>}
     * @throws
     * @author 李庆伟
     * @date 2022/5/30 11:16
     */
    public static List<Map<String, String>> makeData(MultipartFile file) {
        InputStream inputStream = null;//转换成输入流
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] stream = new byte[0];
        try {
            stream = IoUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stream == null || stream.length == 0) {
            return null;
        }
        List<Map<String, String>> dataList = parseExcelToData(stream, 1);//从动态获取全部列和数据体，默认从第一行开始解析数据
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }


    /**
     * 文件导入测试
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //属性项
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\easy_excel_test1.xlsx"));
        byte[] stream = IoUtils.toByteArray(inputStream);
        List<Map<String, String>> dataList = parseExcelToData(stream, 1);//动态获取全部列和数据体，默认从第一行开始解析数据
        List<String> objects = new ArrayList<>();
        dataList.forEach(d -> {
            objects.addAll(d.keySet());
        });
        System.out.println("属性项:" + objects);
        System.out.println("值:" + dataList);
        inputStream.close();
    }
}