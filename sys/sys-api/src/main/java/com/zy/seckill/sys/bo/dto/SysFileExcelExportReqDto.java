package com.zy.seckill.sys.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


/*
 * @Author zhangyong
 * @Description //SysFileReqDto
 * @Date 2022/07/29 11:14
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class SysFileExcelExportReqDto extends BaseDto {


    @ApiModelProperty(name = "type", value = "文件类型(图标/excel导入模板/word导入模板/pdf导入模板/音频/视频)", example = "123",dataType="Long")
    private Long type;

    @ApiModelProperty(name = "fileKey", value = "文件键名称", example = "xxx",dataType="String")
    private String fileKey;
}