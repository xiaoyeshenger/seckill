package com.zy.seckill.common.dao.impl;

import com.zy.seckill.common.bo.dto.LoginLogPageReqDto;
import com.zy.seckill.common.bo.dto.OperateLogPageReqDto;
import com.zy.seckill.common.bo.po.OperateLog;
import com.zy.seckill.common.dao.OperateLogDao;
import com.zy.seckill.common.mongo.MongoBaseDaoImpl;
import com.zy.seckill.common.utils.ObjUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

;


/*
 * @Author zhangyong
 * @Description //OperateLogDao接口实现类
 * @Date 2022/03/02 15:39
 * @Param
 * @return
 **/
@Repository
public class OperateLogDaoImpl extends MongoBaseDaoImpl<OperateLog> implements OperateLogDao {

    @Override
    public OperateLog getOperateLogById(String id) {
        OperateLog operateLog = findById(id);
        return operateLog;
    }

    @Override
    public OperateLog getOperateLogByColumn(String Column,Object value) {
        Query query = new Query(Criteria.where(Column).is(value));
        OperateLog operateLog = findOne(query);
        return operateLog;
    }

        @Override
        public List<OperateLog> getOperateLogListByColumn(String Column,Object value) {
            Query query = new Query(Criteria.where(Column).is(value));
            List<OperateLog> operateLogList = findAll(query);
            return operateLogList;
        }

    @Override
    public Page<OperateLog> getOperateLogListPageVo(OperateLogPageReqDto operateLogPageReqDto) {
        //1.构建查询条件
        Query query = buildQuery(operateLogPageReqDto);

        //2.排序
        Sort sort =  Sort.by(Sort.Direction.DESC, "operateTime");

        //3.执行查询
        Page<OperateLog> pageList = findPageList(query, operateLogPageReqDto.getPageNum(), operateLogPageReqDto.getPageSize(), sort);
        return pageList;
    }

    private Query buildQuery(OperateLogPageReqDto operateLogPageReqDto){
        //1.声明查询对象
        Query query = new Query();

        //2.声明查询条件对象
        Criteria criteria = new Criteria();
        String regionCode = operateLogPageReqDto.getRegionCode();
        if(!ObjUtil.isEmpty(regionCode)){
            criteria.and("regionCode").is(regionCode);
        }

        //3.根据参数封装查询条件
        String moduleName = operateLogPageReqDto.getModuleName();
        if (!ObjUtil.isEmpty(moduleName)) {
            String pattern = ".*?" + StringUtils.trim(moduleName) + ".*";
            criteria.and("moduleName").regex(pattern);
        }

        String methodCnName = operateLogPageReqDto.getMethodCnName();
        if (!ObjUtil.isEmpty(methodCnName)){
            String pattern = ".*?" + StringUtils.trim(methodCnName) + ".*";
            criteria.and("methodCnName").regex(pattern);
        }

        String operatorName = operateLogPageReqDto.getOperatorName();
        if (!ObjUtil.isEmpty(operatorName)){
            criteria.and("operatorName").is(operatorName.trim());
        }

        Long businessType = operateLogPageReqDto.getBusinessType();
        if (!ObjUtil.isEmpty(businessType)){
            criteria.and("businessType").is(businessType);
        }

        Long serviceType = operateLogPageReqDto.getServiceType();
        if (!ObjUtil.isEmpty(serviceType)){
            criteria.and("serviceType").is(serviceType);
        }

        Byte status = operateLogPageReqDto.getStatus();
        if (!ObjUtil.isEmpty(status)){
            criteria.and("status").is(status);
        }

        Long startTime = operateLogPageReqDto.getStartTime();
        Long endTime =operateLogPageReqDto.getEndTime();
        if (startTime != null  && endTime != null){
            criteria.andOperator(
                    Criteria.where("operateTime").gte(startTime),
                    Criteria.where("operateTime").lte(endTime)
            );
        }else {
            if(startTime != null){
                criteria.and("operateTime").gte(startTime);
            }
            if(endTime != null){
                criteria.and("operateTime").lte(endTime);
            }
        }

        //4.查询条件封装进查询对象
        query.addCriteria(criteria);

        return query;
    }

    @Override
    public List<OperateLog> getOperateLogList(OperateLogPageReqDto operateLogPageReqDto) {

        //1.构建查询条件
        Query query = buildQuery(operateLogPageReqDto);

        //2.执行查询
        List<OperateLog> operateLogList = findAll(query);
        return operateLogList;
    }
}