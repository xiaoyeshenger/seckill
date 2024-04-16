package com.zy.seckill.sys.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.bo.dto.PageReqDto;
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
 * @Description //DeptReqDto
 * @Date 2022/02/21 15:06
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class DeptReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "id", example = "236952136338599",dataType="Long")
    @NotNull(message = "id 不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;

    @ApiModelProperty(name = "orgId", value = "组织ID", example = "1369521363385213",dataType="Long")
    @NotNull(message = "组织ID不能为空")
    private Long orgId;

    @ApiModelProperty(name = "parentId", value = "父级部门ID", example = "136952136338589",dataType="Long")
    @NotNull(message = "父级部门ID不能为空")
    private Long parentId;

    @ApiModelProperty(name = "name", value = "部门名称", example = "运营二部",dataType="String")
    @NotNull(message = "部门名称不能为空")
    private String name;

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "3",dataType="String")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;

    @ApiModelProperty(name = "principalName", value = "部门负责人", example = "王五",dataType="String")
    @NotNull(message = "部门负责人不能为空")
    private String principalName;

    @ApiModelProperty(name = "principalMobile", value = "部门负责人电话", example = "xxx",dataType="String")
    @NotNull(message = "部门负责人电话不能为空")
    private String principalMobile;

    @ApiModelProperty(name = "status", value = "状态(0=停用,1=正常)", example = "1",dataType="Byte")
    @NotNull(message = "状态不能为空")
    private Byte status;

}