package com.jsxa.vapp.common.bo.dto;

import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class PageReqDto extends BaseDto {

    @ApiModelProperty(name = "pageIndex",value="分页索引", example = "1",dataType="Integer")
    @NotNull(message = "分页索引不能为空",groups = ValidationGroup.ValidationPage.class)
    @Min(value = 1,message = "分页索引不能小于1")
    private Integer pageNum;

    @ApiModelProperty(name = "pageSize",value="分页大小", example = "10",dataType="Integer")
    @NotNull(message = "分页大小不能为空",groups = ValidationGroup.ValidationPage.class)
    @Min(value = 1,message = "分页大小不能小于1",groups = ValidationGroup.ValidationPage.class)
    @Max(value = 100,message = "分页大小不能大于100")
    private Integer pageSize;

    @ApiModelProperty(name = "searchKey",value="搜索关键字(一般用于同时搜索多个字段)", example = "abc",dataType="String")
    private String searchKey;

}
