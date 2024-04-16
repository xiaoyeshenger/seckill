package com.zy.seckill.common.bo.po;

import com.zy.seckill.common.mongo.MongoIdEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

//http推送日志
@Getter
@Setter
@Builder
@Accessors(chain=true)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "HttpPushLog")
public class HttpPushLog extends MongoIdEntity {


    @ApiModelProperty(value = "推送类型(911,912)")
    private Long pushType;

    @ApiModelProperty(value = "操作时间")
    private Long operateTime;

    @ApiModelProperty(value = "操作时间字符串")
    private String operateDate;

    @ApiModelProperty(value = "操作状态(0=失败,1=成功)")
    private Byte status;

    @ApiModelProperty(value = "是否是最新数据(0=不是,1=是)")
    private Byte latestData;

    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "http请求地址")
    private String httpReqUrl;

    @ApiModelProperty(value = "http请求头")
    private String httpReqHeader;

    @ApiModelProperty(value = "http请求参数")
    private String httpReqParam;

    @ApiModelProperty(value = "http返回结果")
    private String httpResult;

    @ApiModelProperty(value = "关键字(比如ID)")
    private String keyWord;

    @ApiModelProperty(value = "错误信息")
    private String errorMsg;

    @ApiModelProperty(value = "设备UUID")
    private String deviceUuid;

    @ApiModelProperty(value = "产品类型")
    private Long productType;

    @ApiModelProperty(value = "产品编码")
    private Long productId;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "区域码")
    private String regionCode;

}