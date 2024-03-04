package com.jsxa.vapp.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.common.utils.CacheUtil;
import com.jsxa.vapp.sys.bo.dto.PostReqDto;
import com.jsxa.vapp.sys.bo.dto.PostPageReqDto;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.common.utils.VoPoConverterUtil;
import com.jsxa.vapp.sys.bo.po.Post;
import com.jsxa.vapp.sys.mapper.PostDynamicSqlSupport;
import com.jsxa.vapp.sys.mapper.PostMapper;
import com.jsxa.vapp.sys.service.PostService;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;


import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/*
 * @Author zhangyong
 * @Description //PostService接口实现类
 * @Date 2022/02/21 15:03
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final CacheUtil cacheUtil;

    private final PostMapper postMapper;


    @Override
    @Transactional
    public Map<String, Object> addPost(Map<String, Object> headerMap,PostReqDto postReqDto) {

        //1.参数校验
        //(1).园区是否存在
        Long parkId = postReqDto.getParkId();
        cacheUtil.getParkName(parkId);


        //(2).名称是否存在
        String reqName = postReqDto.getName();
        Post po = postMapper.selectByExampleOne()
                .where(PostDynamicSqlSupport.parkId, isEqualTo(parkId))
                .and(PostDynamicSqlSupport.name, isEqualTo(reqName))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(po)){
            throw new IllegalArgumentException("名称为:"+reqName+"的职位已存在");
        }


        //2.设置参数
        //(1)复制PostReqDto中的请求参数到Post
        Post post = VoPoConverterUtil.copyProperties(postReqDto, Post.class);

        //(2)设置其他属性
        post.setDelFlag((byte)0)
            .setCreateTime(System.currentTimeMillis());

        //3.保存
        postMapper.insert(post);

        //3.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加职位信息成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> deletePostById(Map<String, Object> headerMap,Long id){
        Post post = postMapper.selectByPrimaryKey(id);
        if(post == null){
            throw new IllegalArgumentException("id为:"+id+"的职位信息不存在");
        }

        postMapper.deleteByExample()
                    .where(PostDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除职位成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updatePost(Map<String, Object> headerMap,PostReqDto postReqDto) {

        //1.参数校验
        //1.判断postReqDto是否存在
        Post post = postMapper.selectByPrimaryKey(postReqDto.getId());
        if(ObjUtil.isEmpty(post)){
            throw new IllegalArgumentException("id为:"+postReqDto.getId()+"的职位不存在");
        }

        //(2).园区是否存在
        Long parkId = postReqDto.getParkId();
        cacheUtil.getParkName(parkId);

        //(3).如果修改了名称,判断新名称是否存在
        String reqName = postReqDto.getName();
        String name = post.getName();
        if(!reqName.equals(name)){
            Post po = postMapper.selectByExampleOne()
                    .where(PostDynamicSqlSupport.parkId, isEqualTo(parkId))
                    .and(PostDynamicSqlSupport.name, isEqualTo(reqName))
                    .build()
                    .execute();
            if(!ObjUtil.isEmpty(po)){
                throw new IllegalArgumentException("名称为:"+reqName+"的职位已存在");
            }
        }


        //2.更新Post
        VoPoConverterUtil.beanConverterNotNull(postReqDto, post);

        //3.保存
        postMapper.updateByPrimaryKey(post);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新职位信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getPostById(Map<String, Object> headerMap,Long id){
        Post e = postMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的职位信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("code", e.getCode());
        attr.put("orderNum", e.getOrderNum());
        attr.put("delFlag", e.getDelFlag());
        attr.put("status", e.getStatus());
        attr.put("parkId", e.getParkId());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getPostListPageVo(Map<String, Object> headerMap,PostPageReqDto postPageReqDto){

        //1.设置分页条件
        PageHelper.startPage(postPageReqDto.getPageNum(), postPageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Post>>>.QueryExpressionWhereBuilder builder = postMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        //(1).关键字查询
        builder.and(PostDynamicSqlSupport.parkId, isEqualTo(postPageReqDto.getParkId()));

        String name = postPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
            builder.and(PostDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        String code = postPageReqDto.getCode();
        if(!ObjUtil.isEmpty(code)){
            builder.and(PostDynamicSqlSupport.code, isEqualTo(code));
        }

        Byte status = postPageReqDto.getStatus();
        if(!ObjUtil.isEmpty(status)){
            builder.and(PostDynamicSqlSupport.status, isEqualTo(status));
        }

        Long startTime = postPageReqDto.getStartTime();
        Long endTime = postPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
            builder.and(PostDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            builder.and(PostDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
                builder.and(PostDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
                builder.and(PostDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
            }
        }

        //(2).排序
        builder.orderBy(PostDynamicSqlSupport.orderNum.descending());

        //(3).查询
        List<Post> list = builder.build().execute();

        //4.构建pageVo
        PageVo<Post> pageVo = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("code", e.getCode());
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