package com.zy.seckill.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.utils.CacheUtil;
import com.zy.seckill.sys.bo.dto.DeptReqDto;
import com.zy.seckill.sys.bo.dto.DeptPageReqDto;
import com.zy.seckill.common.utils.ObjUtil;
import com.zy.seckill.common.utils.VoPoConverterUtil;
import com.zy.seckill.sys.bo.po.Dept;
import com.zy.seckill.sys.bo.po.Organization;
import com.zy.seckill.sys.mapper.DeptDynamicSqlSupport;
import com.zy.seckill.sys.mapper.DeptMapper;
import com.zy.seckill.sys.mapper.OrganizationMapper;
import com.zy.seckill.sys.service.DeptService;
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
 * @Description //DeptService接口实现类
 * @Date 2022/02/21 15:06
 * @Param
 * @return
 **/
@Service
@Slf4j
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

    private final CacheUtil cacheUtil;

    private final DeptMapper deptMapper;

    private final OrganizationMapper organizationMapper;


    @Override
    @Transactional
    public Map<String, Object> addDept(Map<String, Object> headerMap,DeptReqDto deptReqDto) {

        //1.参数判断
        //组织是否存在
        Long orgId = deptReqDto.getOrgId();
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);
        if(ObjUtil.isEmpty(organization)){
            throw new IllegalArgumentException("id为:"+orgId+"的组织不存在");
        }

        //(2).名称是否存在
        String reqName = deptReqDto.getName();
        Dept dep = deptMapper.selectByExampleOne()
                .where(DeptDynamicSqlSupport.orgId, isEqualTo(deptReqDto.getOrgId()))
                .and(DeptDynamicSqlSupport.name, isEqualTo(reqName))
                .build()
                .execute();
        if(!ObjUtil.isEmpty(dep)){
            throw new IllegalArgumentException("名称为:"+reqName+"的部门已存在");
        }

        //2.设置参数
        //(1)复制DeptReqDto中的请求参数到Dept
        Dept dept = VoPoConverterUtil.copyProperties(deptReqDto, Dept.class);

        //(2).父级部门是否存在parentId=0为跟级别部门
        Long parentId = deptReqDto.getParentId();
        if(parentId !=0){
            Dept de = deptMapper.selectByPrimaryKey(parentId);
            if(ObjUtil.isEmpty(de)){
                throw new IllegalArgumentException("id为: "+parentId+" 的父级部门不存在");
            }
            dept.setParentName(de.getName());
        }

        //(3)设置其他属性
        dept.setCreateTime(System.currentTimeMillis())
            .setDelFlag((byte)0)
            .setAncestors(getAncestors(dept,new ArrayList<>()));

        //3.保存
        deptMapper.insert(dept);

        //3.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","添加部门信息成功");
        return resultMap;
    }

    //通过部门获取祖级ID列表
    public String getAncestors(Dept dept,List<String> ancestorList){
        Long parentId = dept.getParentId();
        ancestorList.add(String.valueOf(parentId));
        if(parentId != 0){
            Dept de = deptMapper.selectByPrimaryKey(parentId);
            if(!ObjUtil.isEmpty(de)){
                getAncestors(de,ancestorList);
            }
        }
        String recordStr = String.join(",",ancestorList);
        return recordStr;
    }

    @Override
    @Transactional
    public Map<String, Object> deleteDeptById(Map<String, Object> headerMap,Long id){
        Dept dept = deptMapper.selectByPrimaryKey(id);
        if(dept == null){
            throw new IllegalArgumentException("id为:"+id+"的部门信息不存在");
        }

        deptMapper.deleteByExample()
                    .where(DeptDynamicSqlSupport.id, isEqualTo(id))
                    .build()
                    .execute();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg","删除部门成功");
        return resultMap;
    }

    @Override
    @Transactional
    public Map<String, Object> updateDept(Map<String, Object> headerMap,DeptReqDto deptReqDto) {

        //1.参数判断
        Long orgId = deptReqDto.getOrgId();
        Organization organization = organizationMapper.selectByPrimaryKey(orgId);
        if(ObjUtil.isEmpty(organization)){
            throw new IllegalArgumentException("id为:"+orgId+"的组织不存在");
        }

        //(1).判断dept是否存在
        Dept dept = deptMapper.selectByPrimaryKey(deptReqDto.getId());
        if(ObjUtil.isEmpty(dept)){
            throw new IllegalArgumentException("id为:"+deptReqDto.getId()+"的部门不存在");
        }

        //(3).父级部门是否存在
        Long parentId = deptReqDto.getParentId();
        if(parentId !=0){
            Dept de = deptMapper.selectByPrimaryKey(parentId);
            if(ObjUtil.isEmpty(de)){
                throw new IllegalArgumentException("id为: "+parentId+" 的父级部门不存在");
            }
            dept.setParentName(de.getName());
        }


        //(3).如果修改了名称,判断新名称是否存在
        String reqName = deptReqDto.getName();
        String name = dept.getName();
        if(!reqName.equals(name)){
            Dept dep = deptMapper.selectByExampleOne()
                    .where(DeptDynamicSqlSupport.orgId, isEqualTo(orgId))
                    .and(DeptDynamicSqlSupport.name, isEqualTo(reqName))
                    .build()
                    .execute();
            if(!ObjUtil.isEmpty(dep)){
                throw new IllegalArgumentException("名称为:"+reqName+"的部门已存在");
            }
        }

        //2.更新Dept
        //(1)复制DeptDto中的请求参数到Dept
        VoPoConverterUtil.beanConverterNotNull(deptReqDto, dept);
        dept.setAncestors(getAncestors(dept,new ArrayList<>()));


        //3.保存
        deptMapper.updateByPrimaryKey(dept);

        //4.返回结果
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("msg","更新部门信息成功");
        return resultMap;
    }

    @Override
    public Map<String, Object> getDeptById(Map<String, Object> headerMap,Long id){
        Dept e = deptMapper.selectByPrimaryKey(id);
        if(ObjUtil.isEmpty(e)){
            throw new IllegalArgumentException("id为:"+id+"的部门信息不存在");
        }

        Map<String, Object> attr = new HashMap<>();
        attr.put("id", e.getId());
        attr.put("name", e.getName());
        attr.put("parentId", e.getParentId());
        attr.put("parentName", e.getParentName());
        attr.put("ancestors", e.getAncestors());
        attr.put("orderNum", e.getOrderNum());
        attr.put("status", e.getStatus());
        attr.put("delFlag", e.getDelFlag());
        attr.put("principalName", e.getPrincipalName());
        attr.put("principalMobile", e.getPrincipalMobile());
        attr.put("orgId", e.getOrgId());
        attr.put("orgName", e.getOrgName());
        attr.put("createTime", e.getCreateTime());
        return attr;
    }

    @Override
    public PageVo<Map<String, Object>> getDeptListPageVo(Map<String, Object> headerMap,DeptPageReqDto deptPageReqDto){

        //1.设置分页条件
        PageHelper.startPage(deptPageReqDto.getPageNum(), deptPageReqDto.getPageSize());

        //2.查询语句构建
        QueryExpressionDSL<MyBatis3SelectModelAdapter<List<Dept>>>.QueryExpressionWhereBuilder builder = deptMapper.selectByExample().where();

        //3.根据查询条件封装查询命令
        builder.and(DeptDynamicSqlSupport.orgId, isEqualTo(deptPageReqDto.getOrgId()));

        String name = deptPageReqDto.getName();
        if(!ObjUtil.isEmpty(name)){
            builder.and(DeptDynamicSqlSupport.name, isLike("%"+name+"%"));
        }

        Byte status = deptPageReqDto.getStatus();
        if(!ObjUtil.isEmpty(status)){
            builder.and(DeptDynamicSqlSupport.status, isEqualTo(status));
        }

        Long startTime = deptPageReqDto.getStartTime();
        Long endTime = deptPageReqDto.getEndTime();
        if (startTime != null && endTime != null) {
            builder.and(DeptDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            builder.and(DeptDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
        } else {
            if (startTime != null) {
                builder.and(DeptDynamicSqlSupport.createTime, isGreaterThanOrEqualTo(startTime));
            }
            if (endTime != null) {
                builder.and(DeptDynamicSqlSupport.createTime, isLessThanOrEqualTo(endTime));
            }
        }

        //(2).排序
        builder.orderBy(DeptDynamicSqlSupport.orderNum.descending());

        //(3).查询
        List<Dept> list = builder.build().execute();

        //4.构建pageVo
        PageVo<Dept> pageVo = new PageVo<>(list);

        //5.对象转为map分页输出
        return PageVo.of(
                pageVo,
                e -> {
                    Map<String, Object> attr = new HashMap<>();
                    attr.put("id", e.getId());
                    attr.put("name", e.getName());
                    attr.put("parentId", e.getParentId());
                    attr.put("parentName", e.getParentName());
                    attr.put("ancestors", e.getAncestors());
                    attr.put("orderNum", e.getOrderNum());
                    attr.put("status", e.getStatus());
                    attr.put("delFlag", e.getDelFlag());
                    attr.put("principalName", e.getPrincipalName());
                    attr.put("principalMobile", e.getPrincipalMobile());
                    attr.put("orgId", e.getOrgId());
                    attr.put("orgName", e.getOrgName());
                    attr.put("createTime", e.getCreateTime());
                    return attr;
                }
        );
    }
}