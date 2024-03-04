package com.jsxa.vapp.common.bo.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/*
 * @Author wangchao
 * @Description  设备日志记录
 * @Date 2023/1/3 10:20
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain = true)
@Document(collection = "LogRecordData")//映射到mongodb中自动建表
public class LogRecordData {
    @Tolerate
    public LogRecordData() {
    }

    @Id
    @Field("_id")
    private String id;

    //设备id
    private String deviceId;

    //设备名称
    private String deviceName;

    //告警时间
    private Long alarmTime;

    //报警图片
    private String alarmImage;

    //数据类型 1:告警 2:日志
    private Integer dataType;

    //告警类型
    private String alarmType;

    //1:删除,0未删除
    private Integer isDelete;

    //扩展字段(json)
    private String extData;

    private String uuid;

    private String ddlEventId;

    private Long createTime;

}
