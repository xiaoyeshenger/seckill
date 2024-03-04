package com.jsxa.vapp.sys.controller;

import com.jsxa.vapp.common.bo.vo.ResultVo;
import com.jsxa.vapp.common.bo.vo.UserVo;
import com.jsxa.vapp.common.constant.Constant;
import com.jsxa.vapp.common.jobManager.AsyncJobManager;
import com.jsxa.vapp.common.jobManager.factory.AsyncFactory;
import com.jsxa.vapp.common.utils.JwtTokenUtil;
import com.jsxa.vapp.common.utils.MessageUtil;
import com.jsxa.vapp.common.utils.ObjUtil;
import com.jsxa.vapp.common.utils.Sm4Util;
import com.jsxa.vapp.sys.bo.dto.FreeLoginReqDto;
import com.jsxa.vapp.sys.bo.dto.UserGetSmsReqDto;
import com.jsxa.vapp.sys.bo.dto.UserLoginReqDto;
import com.jsxa.vapp.sys.bo.vo.PermsVo;
import com.jsxa.vapp.sys.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;


/*
 * @Author 张勇
 * @Description LoginController类
 * @Date 2021/05/21 18:43
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/auth")
@Api(tags = "用户登陆相关接口")
@Validated
@RequiredArgsConstructor
public class LoginController {

    private final JwtTokenUtil jwtTokenUtil;

    private final LoginService loginService;


    /*
     * @Author zhangyong
     * @Description //(1) 获取短信验证码
     * @Date 下午 4:45 2019/9/5 0005
     * @Param
     * @return
     **/
    @ApiOperation("获取短信验证码")
    @PostMapping(value="getSmsCode",produces = { "application/json"})
    public ResultVo<Map<String, Object>> getSmsCode(@Validated @RequestBody UserGetSmsReqDto userGetSmsReqDto){


        //1.邮箱地址/手机号
        String mobileNumber = userGetSmsReqDto.getMobileNumber();

        //2.通获取短信验证码
        String code = loginService.getSmsCode(mobileNumber);
        if(ObjUtil.isEmpty(code)){
            throw new IllegalArgumentException("获取验证码失败");
        };

        //3.返回成功
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("smsCode",code);
        return ResultVo.ok(resultMap);
    }


    /*
     * @Author: zhangyong
     * description: (2) 用户登陆
     * @Date: 2021-05-19 9:39
     * @Param:
     * @Return:
     */
    @ApiOperation("用户登陆")
    @PostMapping(value="login",produces = { "application/json"})
    public ResultVo<Map<String, Object>> login(
            @Validated @RequestBody UserLoginReqDto userLoginReqDto
    ){
        //1.登陆
        UserVo userVo = loginService.login(userLoginReqDto);

        //2.生成token
        String token = jwtTokenUtil.generateToken(userVo);

        //3.token存入redis
        Long id = userVo.getId();
        String userId = String.valueOf(id);
        loginService.putTokenToRedis(userId,token);

        //4.获取权限列表
        List<PermsVo> permsVoList = loginService.getPermsVoListByUserId(id);

        //5.保存登录日志
        AsyncJobManager.me().execute(AsyncFactory.saveLoginLog(userVo.getUsername(),userVo.getRegionCode(), Constant.LOGIN_SUCCESS, MessageUtil.message("user.login.success")));

        //6.返回结果
        Map<String, Object> data = new HashMap<>(4);
        data.put("userInfo", userVo);
        data.put("token", token);
        data.put("routerVoList", permsVoList);

        return ResultVo.ok(data);
    }



    @ApiOperation("用户免登陆获取账号系统")
    @PostMapping(value="freeLogin",produces = { "application/json"})
    public ResultVo<Map<String, Object>> freeLogin(
            @Validated @RequestBody FreeLoginReqDto userLoginReqDto
    ){
        //1.登陆
        UserVo userVo = loginService.freeLogin(userLoginReqDto);

        //2.生成token
        String token = jwtTokenUtil.generateToken(userVo);

        //3.token存入redis
        Long id = userVo.getId();
        String userId = String.valueOf(id);
        loginService.putTokenToRedis(userId,token);

        //4.获取权限列表
        List<PermsVo> permsVoList = loginService.getPermsVoListByUserId(id);

        //5.保存登录日志
//        AsyncJobManager.me().execute(AsyncFactory.saveLoginLog(userVo.getUsername(),userVo.getRegionCode(), Constant.LOGIN_SUCCESS, MessageUtil.message("user.freeLogin.success")));

        //6.返回结果
        Map<String, Object> data = new HashMap<>(4);
        data.put("userInfo", userVo);
        data.put("token", token);
        data.put("routerVoList", permsVoList);

        return ResultVo.ok(data);
    }
}