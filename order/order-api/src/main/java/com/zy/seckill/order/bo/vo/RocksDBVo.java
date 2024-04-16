package com.zy.seckill.order.bo.vo;

import com.zy.seckill.common.bo.vo.BaseVo;
import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class RocksDBVo extends BaseVo {

    private String cfName;
    private String key;
    private String value;

}
