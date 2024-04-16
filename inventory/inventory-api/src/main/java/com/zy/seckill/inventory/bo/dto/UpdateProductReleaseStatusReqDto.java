package com.zy.seckill.inventory.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/*
 * @Author wangchao
 * @Description //ProductReqDto
 * @Date 2022/12/23 14:18
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductReleaseStatusReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    @NotNull(message = "主键不能为空")
    private Long id;



    @ApiModelProperty(value = "放苗状态(0=停止,1=开启)")
    @NotNull(message = "放苗状态不能为空")
    private Byte status;
    

}