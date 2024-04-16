package com.zy.seckill.sys.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import com.zy.seckill.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;

import java.util.List;


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
public class SysFileReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "123",dataType="Long")
    @NotNull(message = "主键不能为空",groups = ValidationGroup.ValidationUpdate.class)
    private Long id;


    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    @NotNull(message = "名称不能为空")
    private String name;
    

    @ApiModelProperty(name = "type", value = "文件类型(logo/图标/excel导入模板/word导入模板/pdf导入模板/图片/音频/视频)", example = "xxx",dataType="Long")
    @NotNull(message = "文件类型(图标/excel导入模板/word导入模板/pdf导入模板/音频/视频)不能为空")
    private Long type;


    @ApiModelProperty(name = "fileKey", value = "文件键名称", example = "xxx",dataType="String")
    @NotNull(message = "文件键名称不能为空")
    private String fileKey;


    @ApiModelProperty(name = "orderNum", value = "排序号", example = "123",dataType="Integer")
    @NotNull(message = "排序号不能为空")
    private Integer orderNum;
}