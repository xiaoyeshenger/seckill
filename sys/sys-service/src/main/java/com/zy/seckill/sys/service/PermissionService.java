package com.zy.seckill.sys.service;

import com.zy.seckill.common.bo.dto.PageReqDto;
import com.zy.seckill.common.bo.po.Permission;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.PermissionPageReqDto;
import com.zy.seckill.sys.bo.dto.PermissionReqDto;
import com.zy.seckill.sys.bo.vo.PermsVo;

import java.util.List;
import java.util.Map;

/*
 * @Author 张勇
 * @Description //PermissionService接口
 * @Date xxxx/05/24 11:18
 * @Param
 * @return
 **/
public interface PermissionService {


    //添加权限
    Map<String, Object> addPermission(String userInfo, PermissionReqDto permissionReqDto);

    //通过id删除权限
    Map<String, Object> deletePermissionById(String userInfo, Long id);

    //更新权限
    Map<String, Object> updatePermission(String userInfo, PermissionReqDto permissionReqDto);

    //通过id查询权限
    Map<String, Object> getPermissionById(String userInfo, Long id);

    //查询所有权限列表并分页
    PageVo<Map<String, Object>> getPermissionListPageVo(PermissionPageReqDto permissionPageReqDto);

    //查询所有APP的权限列表并分页
    PageVo<Map<String, Object>> getAppPermissionListPageVo(PageReqDto pageReqDto);


    //通过Id获取所有的父级菜单id列表
    List<Long> getParentPermIdList(Long permId, List<Long> idList);

    //通过Id获取顶级菜单
    Permission getTopPerm(Long permId);


    //查询路由RouterVo(权限)列表树形结构数据
    List<PermsVo> getPermsVoListTreeData(Long useType);

    //通过角色id查询路由RouterVo(权限)列表树形结构数据
    List<PermsVo> getPermsVoListTreeDataByRoleId(Long roleId);



}