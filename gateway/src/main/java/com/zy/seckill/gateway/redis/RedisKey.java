package com.zy.seckill.gateway.redis;


/*
 * @Author: zhangyong
 * description: 缓存key
 * @Date: xxxx-08-31 17:30
 * @Param:
 * @Return:
 */
public interface RedisKey {

    //1.用户相关
    //用户短信验证码
    String USER_MOBILE_SMSCODE = "userMobileSmsCode:";

    //用户token
    String USER_TOKEN_KEY = "userTokenKey:";

    //用户token过期时间
    String USER_EXPIRE_KEY = "userExpireKey:";



    //2.系统相关

    //系统设置
    String SYS_SETTING_KEY = "sys_setting";
    String FILE_UPLOAD_PREFIX = "fileUploadPrefix";
    String FILE_ACCESS_PREFIX = "fileAccessPrefix";

    //参数设置
    String PARAM_SETTING_KEY = "paramSettingKey";

    //系统数据(使用hash类型存储)
    String SYS_DATA = "sys_data";
    String DATA_DICT = "dataDict";
    String REGION = "region";

    //系统缓存(使用Set无序集合存储--->用户ID集合，园区ID集合，区域ID集合，字典ID集合等)
    String SYS_SET_PARK = "sys_set_park";
    String SYS_SET_USER = "sys_set_user";
    String SYS_SET_REGION = "sys_set_region";
    String SYS_SET_DATADICT = "sys_set_dataDict";
}