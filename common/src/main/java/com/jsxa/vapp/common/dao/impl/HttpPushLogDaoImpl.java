package com.jsxa.vapp.common.dao.impl;
 
import com.jsxa.vapp.common.bo.po.HttpPushLog;
import com.jsxa.vapp.common.cache.DictCache;
import com.jsxa.vapp.common.cache.RegionCache;
import com.jsxa.vapp.common.dao.HttpPushLogDao;;
import com.jsxa.vapp.common.bo.dto.HttpPushLogPageReqDto;
import com.jsxa.vapp.common.utils.ObjUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.jsxa.vapp.common.mongo.MongoBaseDaoImpl;

import javax.annotation.Resource;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/*
 * @Author zhangyong
 * @Description //HttpPushLogDao接口实现类
 * @Date 2023/03/23 11:02
 * @Param
 * @return
 **/
@Repository
public class HttpPushLogDaoImpl extends MongoBaseDaoImpl<HttpPushLog> implements HttpPushLogDao {

    @Resource
    private RegionCache regionCache;

    @Resource
    private DictCache dictCache;

    @Override
    public HttpPushLog getHttpPushLogById(String id) {
        HttpPushLog httpPushLog = findById(id);
        return httpPushLog;
    }

    @Override
    public HttpPushLog getHttpPushLogByColumn(String Column,Object value) {
        Query query = new Query(Criteria.where(Column).is(value));
        HttpPushLog httpPushLog = findOne(query);
        return httpPushLog;
    }

        @Override
        public List<HttpPushLog> getHttpPushLogListByColumn(String Column,Object value) {
            Query query = new Query(Criteria.where(Column).is(value));
            List<HttpPushLog> httpPushLogList = findAll(query);
            return httpPushLogList;
        }

    @Override
    public Page<HttpPushLog> getHttpPushLogListPageVo(HttpPushLogPageReqDto httpPushLogPageReqDto) {

        //1.排序
        Sort sort =  Sort.by(Sort.Direction.DESC, "operateTime");

        //2.执行查询
        Page<HttpPushLog> pageList = findPageList(buildQuery(httpPushLogPageReqDto),httpPushLogPageReqDto.getPageNum(), httpPushLogPageReqDto.getPageSize(), sort);

        //3.返回结果
        return pageList;
    }

    @Override
    public List<HttpPushLog> getHttpPushLogList(HttpPushLogPageReqDto httpPushLogPageReqDto) {
        List<HttpPushLog> httpPushLogList = findAll(buildQuery(httpPushLogPageReqDto));
        return httpPushLogList;
    }

    private Query buildQuery(HttpPushLogPageReqDto httpPushLogPageReqDto){
        //1.声明查询对象
        Query query = new Query();

        //2.声明查询条件对象
        Criteria criteria = new Criteria();

        //3.根据参数封装查询条件
        String searchKey = httpPushLogPageReqDto.getSearchKey();
        if (StringUtils.isNotEmpty(searchKey)) {
            String pattern = ".*?" + StringUtils.trim(searchKey) + ".*";
            criteria = Criteria.where("productName").regex(pattern);
        }

        Long pushType = httpPushLogPageReqDto.getPushType();
        if (!ObjUtil.isEmpty(pushType)){
            criteria.and("pushType").is(pushType);
        }

        Byte status = httpPushLogPageReqDto.getStatus();
        if (!ObjUtil.isEmpty(status)){
            criteria.and("status").is(status);
        }

        Byte latestData = httpPushLogPageReqDto.getLatestData();
        if (!ObjUtil.isEmpty(latestData)){
            criteria.and("latestData").is(latestData);
        }

        Long productType = httpPushLogPageReqDto.getProductType();
        if (!ObjUtil.isEmpty(productType)){

            Set<Long> codeListByParentId = dictCache.getCodeListByParentId(productType);
            criteria.and("productType").in(codeListByParentId);
        }

        String regionCode = httpPushLogPageReqDto.getRegionCode();
        if (!ObjUtil.isEmpty(regionCode)){
            Set<String> villageCodeSet = regionCache.getCodeListByParentCodeAndChildType(regionCode, 408L);
            if(!ObjUtil.isEmpty(villageCodeSet)){
                criteria.and("regionCode").in(villageCodeSet);
            }
        }

        String deviceKey = httpPushLogPageReqDto.getDeviceKey();
        if (!ObjUtil.isEmpty(deviceKey)){
            criteria.orOperator(
                    Criteria.where("keyWord").is(deviceKey.trim()),
                    Criteria.where("deviceUuid").is(deviceKey.trim())
            );
        }



        Long startTime = httpPushLogPageReqDto.getStartTime();
        Long endTime =httpPushLogPageReqDto.getEndTime();
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

        //5.返回query
        return query;
    }
}