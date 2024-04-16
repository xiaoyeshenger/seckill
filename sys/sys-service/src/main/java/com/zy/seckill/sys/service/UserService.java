package com.zy.seckill.sys.service;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.*;

import java.util.Map;

/*
 * @Author 张勇
 * @Description //UserService接口
 * @Date xxxx/05/21 18:43
 * @Param
 * @return
 **/
public interface UserService {


    //添加用户
    Map<String, Object> addUser(String userInfo, UserReqDto userReqDto);

    //通过id删除用户
    Map<String, Object> deleteUserById(String userInfo, Long id);

    //更新用户
    Map<String, Object> updateUser(String userInfo, UserReqDto userReqDto);

    //更新用户注册状态
    Map<String, Object> updateUserRegType(String userInfo, UserRegTypeReqDto userRegTypeReqDto);

    //通过id查询用户
    Map<String, Object> getUserById(String userInfo, Long id);

    //查询所有用户列表并分页
    PageVo<Map<String, Object>> getUserListPageVo(String userInfo, UserPageReqDto userPageReqDto);

    //更新密码(重置密码)
    Map<String, Object> updatePassword(UserPwdUpdateDto userPasswordDto);

    Map<String, Object> getRegisterByUserId(String userInfo, Long id);

    //通过角色code查询账号列表
    Map<String, Object> getUserListByRoleKey(String userInfo, String roleKey);
}