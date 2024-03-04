package com.jsxa.vapp.sys.service;

import com.jsxa.vapp.common.bo.vo.UserVo;
import com.jsxa.vapp.sys.bo.dto.FreeLoginReqDto;
import com.jsxa.vapp.sys.bo.dto.UserLoginReqDto;
import com.jsxa.vapp.sys.bo.dto.UserRegisterReqDto;
import com.jsxa.vapp.sys.bo.vo.PermsVo;

import java.util.List;
import java.util.Map;

/*
 * @Author 张勇
 * @Description //LoginService接口
 * @Date 2021/05/21 18:43
 * @Param
 * @return
 **/
public interface LoginService {

    //通过手机号获取验证码
    String getSmsCode(String mobileNumber);

    //验证手机验证码
    void validSmsCode(String mobileNumber, String smsCode);

    //用户注册
    UserVo register(UserRegisterReqDto userRegisterReqDto);

    //修改用户注册信息
    UserVo updateRegister(UserRegisterReqDto userRegisterReqDto);

    //登录
    UserVo login(UserLoginReqDto managerLoginReqDto);

    //免密登录
    UserVo freeLogin(FreeLoginReqDto userLoginReqDto);

    //查询用户名是否存
    Map<String, Object> queryUsernameExist(String username);

    //向redis存入token
    void putTokenToRedis(String userId, String token);

    //通过用户Id查询路由(权限)列表
    List<PermsVo> getPermsVoListByUserId(Long id);

}