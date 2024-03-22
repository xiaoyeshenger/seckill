package com.jsxa.vapp.sys.bo.dto;

import com.jsxa.vapp.common.bo.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@Accessors(chain=true)
public class ModusReadReqDto extends BaseDto {

        @Tolerate
        public ModusReadReqDto(){}

        @ApiModelProperty(name = "serialNum", value = "设备序列号", example = "1",dataType="String")
        @NotNull(message = "设备序列号不能为空")
        private String serialNum;

        @ApiModelProperty(name = "registerAddrHigh", value = "寄存器地址高位", example = "00",dataType="String")
        @NotNull(message = "寄存器地址高位不能为空")
        private String registerAddrHigh;

        @ApiModelProperty(name = "registerAddrLow", value = "寄存器地址低位", example = "00",dataType="String")
        @NotNull(message = "寄存器地址低位不能为空")
        private String registerAddrLow;

}
