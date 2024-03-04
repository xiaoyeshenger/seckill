package com.jsxa.vapp.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class YinHaiHttpUtil {


    //构建银海接口需要的签名的请求头
    public static Map<String, Object> buildHeaderMap(String token,String paasid) {

        //(1).时间戳(秒)
        long timestamp = System.currentTimeMillis() / 1000;

        //(2).参数签名
        String signature = SHA256Util.getSHA256(timestamp + token + timestamp);
        Map<String, Object> headerMap = new HashMap();
        headerMap.put("Content-Type", "application/json; charset=utf-8");
        headerMap.put("xz-paasid", paasid);
        headerMap.put("xz-timestamp", String.valueOf(timestamp));
        headerMap.put("xz-signature", signature);
        //headerMap.put("xz-type", headers.get("client-type"));
        //headerMap.put("xz-key", headers.get("client-key"));
        return headerMap;
    }


    //判断响应结果
    public static void judgeResponse(JSONObject jsonObject, String name) {
        Boolean serviceSuccess = jsonObject.getBoolean("serviceSuccess");
        if (!serviceSuccess) {
            JSONArray jsonArray = jsonObject.getJSONArray("errors");
            JSONObject jo = (JSONObject) jsonArray.get(0);
            String errorCode = jo.getString("errorCode");
            String msg = jo.getString("msg");
            log.info("调用" + name + "失败,errorCode:{},msg:{}", errorCode, msg);
            throw new IllegalArgumentException(msg);
        }
    }

    //处理分页数据
//    public static PageVo<JSONObject> processPageData(JSONObject jsonObject) {
//        // (1).获取数据
//        JSONObject data = jsonObject.getJSONObject("data");
//        JSONObject resultData = data.getJSONObject("resultData");
//        JSONArray jsonArray = resultData.getJSONArray("list");
//        if (ObjUtil.isEmpty(jsonArray)) {
//            return PageVo.empty();
//        }
//
//        //(2).转为json列表
//        List<JSONObject> joList = jsonArray.toJavaList(JSONObject.class);
//        PageVo<JSONObject> pageVo = new PageVo<>(joList);
//        pageVo.setPageNumber(resultData.getInteger("pageNum"));
//        pageVo.setPageSize(resultData.getInteger("pageSize"));
//        pageVo.setTotalPageNumber(resultData.getInteger("pages"));
//        pageVo.setTotalAmount(resultData.getLong("total"));
//
//        return pageVo;
//    }


}
