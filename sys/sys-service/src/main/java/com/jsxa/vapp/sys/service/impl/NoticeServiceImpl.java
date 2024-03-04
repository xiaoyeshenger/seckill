package com.jsxa.vapp.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.mapper.UserDynamicSqlSupport;
import com.jsxa.vapp.common.redis.RedisKey;
import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.common.utils.CacheUtil;
import com.jsxa.vapp.sys.bo.dto.NoticeReqDto;
import com.jsxa.vapp.sys.bo.dto.NoticePageReqDto;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.common.utils.VoPoConverterUtil;
import com.jsxa.vapp.sys.bo.po.Notice;
import com.jsxa.vapp.sys.mapper.DeptDynamicSqlSupport;
import com.jsxa.vapp.sys.mapper.NoticeDynamicSqlSupport;
import com.jsxa.vapp.sys.mapper.NoticeMapper;
import com.jsxa.vapp.sys.service.NoticeService;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;


import javax.annotation.Resource;
import java.io.*;
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/*
 * @Author zhangyong
 * @Description //NoticeService接口实现类
 * @Date 2022/02/28 14:46
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final CacheUtil cacheUtil;

    private final RedisService redisService;

    private final NoticeMapper noticeMapper;


    @Override
    @Transactional
    public Map<String, Object> addNotice(Map<String, Object> headerMap,NoticeReqDto noticeReqDto) {
        //1.参数校验
        //园区是否存在
        cacheUtil.getParkName(noticeReqDto.getParkId());

        //数据字典是否存在
        cacheUtil.getDataDictName(noticeReqDto.getType());

        //2.设置参数
        //(1)复制NoticeReqDto中的请求参数到Notice
        Notice notice = VoPoConverterUtil.copyProperties(noticeReqDto, Notice.class);

        //(2)设置其他属性
        notice.setCreateTime(System.currentTimeMillis());

        //3.保存
        noticeMapper.insert(notice);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加通知信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteNoticeById(Map<String, Object> headerMap,Long id){
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        if(notice == null){
            throw new IllegalArgumentException("id为:"+id+"的通知信息不存在");
        }

        noticeMapper.deleteByExample()
                    .where(NoticeDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除通知成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateNotice(Map<String, Object> headerMap,NoticeReqDto noticeReqDto) {

        //1.参数校验
        //园区是否存在
        cacheUtil.getParkName(noticeReqDto.getParkId());

        //数据字典是否存在
        cacheUtil.getDataDictName(noticeReqDto.getType());

        //2.判断notice是否存在
        Notice notice = noticeMapper.selectByPrimaryKey(noticeReqDto.getId());
        if(ObjUtil.isEmpty(notice)){
            throw new IllegalArgumentException("id为:"+noticeReqDto.getId()+"的通知不存在");
        }

        //2.更新Notice
        //(1)复制NoticeDto中的请求参数到Notice
        VoPoConverterUtil.beanConverterNotNull(noticeReqDto, notice);

        //3.保存
        noticeMapper.updateByPrimaryKey(notice);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新通知信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getNoticeById(Map<String, Object> headerMap,Long id){
        Notice e = noticeMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的通知信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("title", e.getTitle());
        attr.put("type", e.getType());
        attr.put("content", e.getContent());
        attr.put("creatorName", e.getCreatorName());
        attr.put("creatorMobile", e.getCreatorMobile());
        attr.put("orderNum", e.getOrderNum());
        attr.put("delFlag", e.getDelFlag());
        attr.put("status", e.getStatus());
        attr.put("parkId", e.getParkId());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getNoticeListPageVo(Map<String, Object> headerMap,NoticePageReqDto noticePageReqDto){

        //1.设置分页条件
        PageHelper.startPage(noticePageReqDto.getPageNum(), noticePageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Notice>>>.QueryExpressionWhereBuilder builder = noticeMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        //(1).关键字查询
        builder.and(UserDynamicSqlSupport.regionCode, isEqualTo(noticePageReqDto.getRegionCode()));

        String title = noticePageReqDto.getTitle();
        if(!ObjUtil.isEmpty(title)){
            builder.and(NoticeDynamicSqlSupport.title, isLike("%"+title+"%"));
        }

        String creatorName = noticePageReqDto.getCreatorName();
        if(!ObjUtil.isEmpty(creatorName)){
            builder.and(NoticeDynamicSqlSupport.creatorName, isLike("%"+creatorName+"%"));
        }

        Long type = noticePageReqDto.getType();
        if(!ObjUtil.isEmpty(type)){
            builder.and(NoticeDynamicSqlSupport.type, isEqualTo(type));
        }

        Byte status = noticePageReqDto.getStatus();
        if(!ObjUtil.isEmpty(status)){
            builder.and(DeptDynamicSqlSupport.status, isEqualTo(status));
        }

        Long startTime = noticePageReqDto.getStartTime();
        Long endTime = noticePageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
            builder.and(NoticeDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            builder.and(NoticeDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
                builder.and(NoticeDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
                builder.and(NoticeDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
            }
        }

        //(2).排序
        builder.orderBy(NoticeDynamicSqlSupport.orderNum.descending());

        //(3).查询
        List<Notice> list = builder.build().execute();

        //4.构建pageVo
        PageVo<Notice> pageVo = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("title", e.getTitle());
                    attr.put("type", e.getType());
                    attr.put("content", e.getContent());
                    attr.put("creatorName", e.getCreatorName());
                    attr.put("creatorMobile", e.getCreatorMobile());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("delFlag", e.getDelFlag());
                    attr.put("status", e.getStatus());
                    attr.put("parkId", e.getParkId());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }
}