package com.jsxa.vapp.sys.bo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/*
 * @Author wangchao
 * @Description
 * @Date 2023/6/13 14:12
 * @Param
 * @return
 **/
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpPushLogReqDto {

    @ApiModelProperty(name = "id", value = "id", example = "510113103",dataType="String")
    @NotNull(message = "id不能为空")
    private String id;

    @ApiModelProperty(name = "headerJson", value = "请求头参数", example = "510113103",dataType="String")
    @NotNull(message = "请求头参数不能为空")
    private String headerJson;



    @ApiModelProperty(name = "dataJson", value = "推送数据的请求json", example = "510113103",dataType="String")
    @NotNull(message = "请求参数不能为空")
    private String dataJson;
}
