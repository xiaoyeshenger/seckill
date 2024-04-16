package com.zy.seckill.taskCenter.bo.dto;


import com.zy.seckill.common.bo.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;


/*
 * @Author zhangyong
 * @Description //BidRecordReqDto
 * @Date 2023/12/18 11:43
 * @Param
 * @return
 **/
@Getter
@Setter
@Builder
@Accessors(chain=true)
@AllArgsConstructor
@NoArgsConstructor
public class BidRecordExcelExportReqDto extends BaseDto {

    @ApiModelProperty(name = "id", value = "主键", example = "xxx",dataType="Long")
    private Long id;
    

    @ApiModelProperty(name = "projectId", value = "项目ID", example = "xxx",dataType="Long")
    private Long projectId;
    

    @ApiModelProperty(name = "projectName", value = "名称名称", example = "xxx",dataType="String")
    private String projectName;
    

    @ApiModelProperty(name = "projectQuantity", value = "项目数量", example = "xxx",dataType="Double")
    private Double projectQuantity;
    

    @ApiModelProperty(name = "curUnitPrice", value = "当前出价单价", example = "xxx",dataType="Double")
    private Double curUnitPrice;
    

    @ApiModelProperty(name = "curTotalPrice", value = "当前出价总价", example = "xxx",dataType="Double")
    private Double curTotalPrice;
    

    @ApiModelProperty(name = "userId", value = "出价人ID", example = "xxx",dataType="Long")
    private Long userId;
    

    @ApiModelProperty(name = "orgName", value = "出价人名称(公司)", example = "xxx",dataType="String")
    private String orgName;
    

    @ApiModelProperty(name = "userMobile", value = "出价人联系电话", example = "xxx",dataType="String")
    private String userMobile;
    

    @ApiModelProperty(name = "bidTime", value = "出价时间", example = "xxx",dataType="Long")
    private Long bidTime;
    

    @ApiModelProperty(name = "bidStatus", value = "拍卖状态(领先/出局)", example = "xxx",dataType="Long")
    private Long bidStatus;
    

    @ApiModelProperty(name = "orderNum", value = "排序号", example = "xxx",dataType="Integer")
    private Integer orderNum;
    

    @ApiModelProperty(name = "createTime", value = "创建时间", example = "xxx",dataType="Long")
    private Long createTime;
    

}