package com.jsxa.vapp.inventory.bo.dto;


import com.jsxa.vapp.common.bo.dto.BaseDto;
import com.jsxa.vapp.common.valid.ValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;


/*
 * @Author zhangyong
 * @Description //VaccinationPersonReqDto
 * @Date 2024/02/02 16:07
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationPersonExcelExportReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    private Long id;
    

    @ApiModelProperty(name = "name", value = "姓名", example = "xxx",dataType="String")
    private String name;
    

    @ApiModelProperty(name = "sex", value = "性别", example = "xxx",dataType="Byte")
    private Byte sex;
    

    @ApiModelProperty(name = "age", value = "年龄", example = "xxx",dataType="Integer")
    private Integer age;
    

    @ApiModelProperty(name = "mobile", value = "联系电话", example = "xxx",dataType="String")
    private String mobile;
    

    @ApiModelProperty(name = "idNumber", value = "身份证号码", example = "xxx",dataType="String")
    private String idNumber;
    

    @ApiModelProperty(name = "openId", value = "openId", example = "xxx",dataType="String")
    private String openId;
    

    @ApiModelProperty(name = "address", value = "通讯地址", example = "xxx",dataType="String")
    private String address;
    

    @ApiModelProperty(name = "workUnit", value = "工作单位", example = "xxx",dataType="String")
    private String workUnit;
    

    @ApiModelProperty(name = "personType", value = "人员类型", example = "xxx",dataType="Long")
    private Long personType;
    

    @ApiModelProperty(name = "doseStatus", value = "疫苗接种状态(从未接种/接种中/接种完成)", example = "xxx",dataType="Long")
    private Long doseStatus;
    

    @ApiModelProperty(name = "firstDoseId", value = "第一剂接种疫苗ID", example = "xxx",dataType="String")
    private String firstDoseId;
    

    @ApiModelProperty(name = "firstDoseName", value = "第一剂接种疫苗名称", example = "xxx",dataType="String")
    private String firstDoseName;
    

    @ApiModelProperty(name = "firstDoseUnit", value = "第一剂接种单位", example = "xxx",dataType="String")
    private String firstDoseUnit;
    

    @ApiModelProperty(name = "firstDoseTime", value = "第一剂接种时间", example = "xxx",dataType="Long")
    private Long firstDoseTime;
    

    @ApiModelProperty(name = "latestDoseId", value = "最近剂次接种疫苗ID", example = "xxx",dataType="String")
    private String latestDoseId;
    

    @ApiModelProperty(name = "latestDoseName", value = "最近剂次接种疫苗名称", example = "xxx",dataType="String")
    private String latestDoseName;
    

    @ApiModelProperty(name = "latestDoseUnit", value = "最近剂次接种单位", example = "xxx",dataType="String")
    private String latestDoseUnit;
    

    @ApiModelProperty(name = "latestDoseTime", value = "最近剂次接种时间", example = "xxx",dataType="Long")
    private Long latestDoseTime;
    

    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    private Long createTime;
    

    @ApiModelProperty(name = "updateTime", value = "更新时间", example = "xxx",dataType="Long")
    private Long updateTime;
    

}