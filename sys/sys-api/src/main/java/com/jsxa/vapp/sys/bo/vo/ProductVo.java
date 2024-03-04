package com.jsxa.vapp.sys.bo.vo;

import com.jsxa.vapp.common.bo.vo.BaseVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Classname DeviceVo
 * @Description TODO
 * @Date 2022/12/15 15:18
 * @Created by Administrator
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo extends BaseVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "产品分类")
    private Long type;

    @ApiModelProperty(value = "产品型号")
    private String productModel;
}
