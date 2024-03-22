package com.jsxa.vapp.order.bo.po;

import com.jsxa.vapp.common.mongo.MongoIdEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 微服务调用失败的消息
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "MicroServiceInvokeFailMsg")
public class MicroServiceInvokeFailMsg extends MongoIdEntity {

    @ApiModelProperty(value = "类型(新增,删除,修改，查询，其他)")
    private Long type;

    @ApiModelProperty(value = "微服务名称(库存服务)")
    private String serviceName;

    @ApiModelProperty(value = "微服务名称英文(Inventory)")
    private String serviceValue;

    @ApiModelProperty(value = "方法名称(库存扣减)")
    private String methodName;

    @ApiModelProperty(value = "方法名称(库存扣减)")
    private String methodValue;

    @ApiModelProperty(value = "参数字符串")
    private String paramStr;

    @ApiModelProperty(value = "关键字(可以是对象的ID)")
    private String errorMsg;

    @ApiModelProperty(value = "创建时间字符串")
    private String createDate;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}