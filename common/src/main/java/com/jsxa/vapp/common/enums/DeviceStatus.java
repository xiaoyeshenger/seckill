package com.jsxa.vapp.common.enums;


import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/*
 * @Author wangchao
 * @Description  AEP产品信息
 * @Date 2023/6/2 10:57
 * @Param
 * @return
 **/
public enum DeviceStatus {


    //独居老人门磁
    oldManDoorLock("15236693","3a2b6bf6bb6f43df95e9065c54759ea0", 1421L),

    //门磁
    doorLock("15103088","0495a0b9aa0a49938153abc373ade88e", 1421L),

    //清泉燃气
    QQgas("16670129", "56098f66a42f482ebd14b72638a1189f",1379L),

   //大弯燃气
    DWGas("15219264", "78237cc2fc874bfb9dc117d1dd80190a",1379L),

    //水流开关
    waterFlow("15266463", "20a04cc795bc4d8bb4f74c02d5582252",1413L),

    //井盖
    manholeCover("15266799", "1923253b57df4ccda430699178017641",1410L),

    //烟雾
    smog("15409814", "687c6a075e3d4be6b1fa06505e1c8626",1381L);





    //1.枚举属性
    private String productId;
    private String masterKey;
    private Long deviceType;

    DeviceStatus(String productId, String masterKey, Long deviceType) {
        this.productId = productId;
        this.masterKey = masterKey;
        this.deviceType = deviceType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey;
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    //5.获取全部枚举
    public static List<DeviceStatus> getAllEnum() {
        List<DeviceStatus> list = Lists.newArrayList();
        list.addAll(Arrays.asList(values()));
        return list;
    }

//    //6.通过value获取枚举
//    public static DeviceStatus getEnumByCode(String code) {
//        DeviceStatus result = null;
//        for (DeviceStatus statusEnum : getAllEnum()) {
//            if (statusEnum.code.equals(code)) {
//                result = statusEnum;
//                break;
//            }
//        }
//        return result;
//    }
//
//    public static DeviceStatus getEnumByValue(Long value) {
//        DeviceStatus result = null;
//        for (DeviceStatus statusEnum : getAllEnum()) {
//            if (statusEnum.value.equals(value)) {
//                result = statusEnum;
//                break;
//            }
//        }
//        return result;
//    }
}
