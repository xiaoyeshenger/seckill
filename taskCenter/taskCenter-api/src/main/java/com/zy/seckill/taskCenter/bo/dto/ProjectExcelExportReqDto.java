package com.zy.seckill.taskCenter.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;


/*
 * @Author zhangyong
 * @Description //ProjectReqDto
 * @Date 2023/12/18 11:14
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class ProjectExcelExportReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    private Long id;
    

    @ApiModelProperty(name = "name", value = "名称", example = "xxx",dataType="String")
    private String name;
    

    @ApiModelProperty(name = "startTime", value = "拍卖开始时间", example = "xxx",dataType="Long")
    private Long startTime;
    

    @ApiModelProperty(name = "endTime", value = "拍卖结束时间", example = "xxx",dataType="Long")
    private Long endTime;
    

    @ApiModelProperty(name = "quantityUnit", value = "数量单位", example = "xxx",dataType="String")
    private String quantityUnit;
    

    @ApiModelProperty(name = "quantity", value = "数量", example = "xxx",dataType="Double")
    private Double quantity;
    

    @ApiModelProperty(name = "upsetPrice", value = "起拍价格", example = "xxx",dataType="Double")
    private Double upsetPrice;
    

    @ApiModelProperty(name = "bidIncr", value = "加价幅度", example = "xxx",dataType="Double")
    private Double bidIncr;
    

    @ApiModelProperty(name = "bidIncrMaxTime", value = "加价最长时间", example = "xxx",dataType="Integer")
    private Integer bidIncrMaxTime;
    

    @ApiModelProperty(name = "oosUrl", value = "文件(列表)url", example = "xxx",dataType="String")
    private String oosUrl;
    

    @ApiModelProperty(name = "auctionStatus", value = "拍卖状态", example = "xxx",dataType="Long")
    private Long auctionStatus;
    

    @ApiModelProperty(name = "projectDetails", value = "项目详细信息", example = "xxx",dataType="String")
    private String projectDetails;
    

    @ApiModelProperty(name = "bidNotes", value = "竞拍须知", example = "xxx",dataType="String")
    private String bidNotes;
    

    @ApiModelProperty(name = "bidRule", value = "竞拍规则", example = "xxx",dataType="String")
    private String bidRule;
    

    @ApiModelProperty(name = "contact", value = "联系人", example = "xxx",dataType="String")
    private String contact;
    

    @ApiModelProperty(name = "contactMobile", value = "联系人手机号", example = "xxx",dataType="String")
    private String contactMobile;
    

    @ApiModelProperty(name = "transStatus", value = "交易状态(成交/流拍)", example = "xxx",dataType="Long")
    private Long transStatus;
    

    @ApiModelProperty(name = "transUnitPrice", value = "成交单价", example = "xxx",dataType="Double")
    private Double transUnitPrice;
    

    @ApiModelProperty(name = "transTotalPrice", value = "成交总价", example = "xxx",dataType="Double")
    private Double transTotalPrice;
    

    @ApiModelProperty(name = "bidRecordId", value = "交易成功后的出价记录ID", example = "xxx",dataType="Long")
    private Long bidRecordId;
    

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    private Integer orderNum;
    

    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    private Long createTime;
    

}