package com.zy.seckill.codeGen.entity;

import io.swagger.annotations.ApiModelProperty;
import javax.annotation.Generated;

public class RocketMqFailMsg {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long type;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String objKey;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer reconsumeTimes;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String msgId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String msgBody;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer queueId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long queueOffset;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long commitlogOffset;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String brokerName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String bornhostString;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String createData;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getId() {
        return id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setId(Long id) {
        this.id = id;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getType() {
        return type;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setType(Long type) {
        this.type = type;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getObjKey() {
        return objKey;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setObjKey(String objKey) {
        this.objKey = objKey == null ? null : objKey.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getReconsumeTimes() {
        return reconsumeTimes;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setReconsumeTimes(Integer reconsumeTimes) {
        this.reconsumeTimes = reconsumeTimes;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMsgId() {
        return msgId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getMsgBody() {
        return msgBody;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody == null ? null : msgBody.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getQueueId() {
        return queueId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getQueueOffset() {
        return queueOffset;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setQueueOffset(Long queueOffset) {
        this.queueOffset = queueOffset;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getCommitlogOffset() {
        return commitlogOffset;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCommitlogOffset(Long commitlogOffset) {
        this.commitlogOffset = commitlogOffset;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getBrokerName() {
        return brokerName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName == null ? null : brokerName.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getBornhostString() {
        return bornhostString;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setBornhostString(String bornhostString) {
        this.bornhostString = bornhostString == null ? null : bornhostString.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCreateData() {
        return createData;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateData(String createData) {
        this.createData = createData == null ? null : createData.trim();
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}