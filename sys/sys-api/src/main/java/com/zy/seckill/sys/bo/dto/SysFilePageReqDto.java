package com.zy.seckill.sys.bo.dto;


import com.zy.seckill.common.bo.dto.PageReqDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //SysFilePageReqDto
 * @Date 2022/07/29 11:14
 * @Param
 * @return
 **/
@Getter
@Setter
@Accessors(chain=true)
public class SysFilePageReqDto extends PageReqDto {


    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    private String name;


    @ApiModelProperty(name = "type", value = "文件类型(图标/excel导入模板/word导入模板/pdf导入模板/音频/视频)", example = "123",dataType="Long")
    private Long type;


    @ApiModelProperty(name = "fileKey", value = "文件键名称", example = "xxx",dataType="String")
    private String fileKey;
}