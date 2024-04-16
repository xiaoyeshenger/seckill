package com.zy.seckill.sys.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //NoticeReqDto
 * @Date 2022/02/28 14:46
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class NoticeReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "123",dataType="Long")
    @NotNull(message = "id 不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;
    

    @ApiModelProperty(name = "title", value = "标题", example = "xxx",dataType="String")
    @NotNull(message = "标题不能为空")
    private String title;
    

    @ApiModelProperty(name = "type", value = "类型", example = "123",dataType="Long")
    @NotNull(message = "类型不能为空")
    private Long type;
    

    @ApiModelProperty(name = "content", value = "内容", example = "xxx",dataType="String")
    @NotNull(message = "内容不能为空")
    private String content;
    

    @ApiModelProperty(name = "creatorName", value = "创建者姓名", example = "xxx",dataType="String")
    @NotNull(message = "创建者姓名不能为空")
    private String creatorName;
    

    @ApiModelProperty(name = "creatorMobile", value = "创建者电话", example = "xxx",dataType="String")
    @NotNull(message = "创建者电话不能为空")
    private String creatorMobile;
    

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "123",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
    

    @ApiModelProperty(name = "delFlag", value = "删除标识", example = "1",dataType="Byte")
    @NotNull(message = "删除标识不能为空")
    private Byte delFlag;
    

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=启用)", example = "1",dataType="Byte")
    @NotNull(message = "状态(0=停用,1=启用)不能为空")
    private Byte status;

    @ApiModelProperty(name = "parkId", value = "园区ID", example = "123",dataType="Long")
    @NotNull(message = "园区ID不能为空")
    private Long parkId;

}