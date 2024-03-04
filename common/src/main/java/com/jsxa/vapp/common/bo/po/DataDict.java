package com.jsxa.vapp.common.bo.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.io.Serializable;


/*
 * @Author: zhangyong
 * description: 数据字典
 * @Date: 2021-02-01 09:15
 * @Param:
 * @Return:
 */
@Getter
@Setter
@Builder
@Accessors(chain=true)
public class DataDict implements Serializable {
    //使用@Builder时，@Builder会覆盖默认的构造器，所以必须加上@Tolerate和该类的空构造函数
    @Tolerate
    public DataDict() {}

    //id
    private Long id;

    //中文
    private String name;

    //英文
    private String value;

    //排序
    private Integer orderNum;

    //父级Id
    private Long parentId;

    //能否多选(0不能，1能)
    private Boolean multiple;

    //是否选中
    private Boolean selected;

    //是否和同类型互斥,0表示不限,1表示其他
    private Integer mutex;

    //状态(0=停用,1=启用)
    private Byte status;

    //创建时间
    private Long createTime;

    private String alias;
}
