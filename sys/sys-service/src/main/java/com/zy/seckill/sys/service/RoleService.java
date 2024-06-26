package com.zy.seckill.sys.service;


import com.zy.seckill.common.bo.dto.PageReqDto;
import com.zy.seckill.common.bo.po.Role;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.RolePageReqDto;
import com.zy.seckill.sys.bo.dto.RoleReqDto;

import java.util.List;
import java.util.Map;

/*
 * @Author 张勇
 * @Description //RoleService接口
 * @Date xxxx/05/24 11:26
 * @Param
 * @return
 **/
public interface RoleService {


    //添加角色
    Map<String, Object> addRole(String userInfo, RoleReqDto roleReqDto);

    //通过id删除角色
    Map<String, Object> deleteRoleById(String userInfo, Long id);

    //更新角色
    Map<String, Object> updateRole(String userInfo, RoleReqDto roleReqDto);

    //通过id查询角色
    Map<String, Object> getRoleById(String userInfo, Long id);

    //查询所有角色列表并分页
    PageVo<Map<String, Object>> getRoleListPageVo(String userInfo, RolePageReqDto rolePageReqDto);

    //通过园区ID查询所有的角色信息列表
    List<Role> getRoleList(String userInfo);
}