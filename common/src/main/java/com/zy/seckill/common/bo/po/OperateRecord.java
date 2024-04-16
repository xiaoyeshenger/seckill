package com.zy.seckill.common.bo.po;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

//操作记录
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class OperateRecord implements Serializable {

    private Long id;

    private String objectType;

    private Long objectId;

    private String operateType;

    private Long operatorId;

    private String operatorName;

    private Long operateTime;
}