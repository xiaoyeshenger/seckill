package com.jsxa.vapp.order.bo.vo;

import com.jsxa.vapp.common.bo.vo.BaseVo;
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
