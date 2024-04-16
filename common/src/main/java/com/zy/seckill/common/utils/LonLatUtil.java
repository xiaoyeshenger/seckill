package com.zy.seckill.common.utils;

import java.util.HashMap;
import java.util.Map;

//经纬度计算工具
public class LonLatUtil {

    private static double PI = 3.14159265;
    private static double EARTH_RADIUS = 6378137;
    private static double RAD = Math.PI / 180.0;

    /*
     * @Author: zhangyong
     * description: 根据提供的经度和纬度、以及半径(米)，取得此半径内的最大最小经纬度
     *              可判断经纬是否属于改范围
     * @Date: xxxx-03-26 9:13
     * @Param:
     * @Return:
     */
    public static Map<String,Double> getAround(double lat, double lon, int raidus)
    {
        Double latitude = lat;
        Double longitude = lon;

        Double degree = (24901 * 1609) / 360.0;
        double raidusMile = raidus;

        Double dpmLat = 1 / degree;
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;

        Double mpdLng = degree * Math.cos(latitude * (PI / 180));
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        Map<String,Double> resultMap = new HashMap<>();
        resultMap.put("minLat",minLat);
        resultMap.put("minLng",minLng);
        resultMap.put("maxLat",maxLat);
        resultMap.put("maxLng",maxLng);
        return resultMap;
    }

    /*
     * @Author: zhangyong
     * description: 计算两个经纬度之间的距离(米)
     * @Date: 2020-01-21 9:10
     * @Param:
     * @Return:
     */
    public static double calculateDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = lat1 * RAD;
        double radLat2 = lat2 * RAD;
        double a = radLat1 - radLat2;
        double b = (lng1 - lng2) * RAD;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        double result = Math.round(s * 100) / 100.0d;
        return result;
    }
}
