package com.jsxa.vapp.sys.bo.dto;

import com.jsxa.vapp.common.bo.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@Accessors(chain=true)
public class ModbusOrderReqDto extends BaseDto {

        @Tolerate
        public ModbusOrderReqDto(){}


        @ApiModelProperty(name = "centreMobileNumber", value = "设备序列号", example = "skjngs2001",dataType="String")
        @NotNull(message = "设备序列号不能为空")
        private String serialNum;

        @ApiModelProperty(name = "order", value = "指令(未加crc校验码的数据)", example = "010300000001",dataType="String")
        @NotNull(message = "指令不能为空")
        private String order;
}
