package com.zy.seckill.sys.controller;

import com.zy.seckill.common.annotation.Log;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.bo.vo.UserVo;
import com.zy.seckill.common.enums.BusinessType;
import com.zy.seckill.common.valid.ValidationGroup;
import com.zy.seckill.sys.bo.dto.*;
import com.zy.seckill.sys.service.LoginService;
import com.zy.seckill.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;


/*
 * @Author 张勇
 * @Description UserController类
 * @Date xxxx/05/21 18:43
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/user")
@Api(tags = "用户相关接口")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final LoginService loginService;


    /**
     * @Author 张勇
     * @Description //(1) 添加用户
     * @Date xxxx/05/21 18:43
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加用户")
    @PostMapping(value = "addUser", produces = { "application/json" })
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<Map<String, Object>> addUser(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @Validated({ValidationGroup.ValidationAdd.class}) @RequestBody UserReqDto userReqDto
    ){
        return ResultVo.ok(userService.addUser(userInfo,userReqDto));
    }

    /**
     * @Author 张勇
     * @Description //(2) 通过id删除用户
     * @Date xxxx/05/21 18:43
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deleteUserById/{id}")
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<Map<String, Object>> deleteUserById(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @PathVariable Long id
    ){
         return ResultVo.ok(userService.deleteUserById(userInfo,id));
    }

    /**
     * @Author 张勇
     * @Description //(3) 更新用户
     * @Date xxxx/05/21 18:43
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新用户")
    @PostMapping(value = "updateUser", produces = {"application/json"})
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<Map<String, Object>> updateUser(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @Validated({ValidationGroup.ValidationUpdate.class}) @RequestBody UserReqDto userReqDto
    ){
        return ResultVo.ok(userService.updateUser(userInfo,userReqDto));
    }

    /**
     * @Author 张勇
     * @Description //(5) 通过id查询用户
     * @Date xxxx/05/21 18:43
     * @Param
     * @return
     */
    @ApiOperation("通过id查询用户")
    @ApiImplicitParam(name = "id", value = "用户id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getUserById/{id}")
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<Map<String, Object>> getUserById(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @PathVariable Long id
    ){
        return ResultVo.ok(userService.getUserById(userInfo,id));
    }

    /**
     * @Author zhangyong
     * @Description //(6) 查询所有的用户信息列表并分页(支持关键字查询)
     * @Date xxxx/05/21 18:43
     * @Param
     * @return
     */
    @ApiOperation("查询所有的用户信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getUserListPageVo", produces = { "application/json" })
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<PageVo<Map<String, Object>>> getUserListPageVo(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @Validated @RequestBody UserPageReqDto userPageReqDto
    ){
        return ResultVo.ok(userService.getUserListPageVo(userInfo,userPageReqDto));
    }


    /**
     * @Author: zhangyong
     * description: (2) 新增用户注册信息(无需token直接注册)
     * @Date: xxxx-05-19 9:39
     * @Param:
     * @Return:
     */
    @ApiOperation("用户注册")
    @PostMapping(value="register",produces = { "application/json"})
    public ResultVo<Map<String, Object>> register(
            @Validated({ValidationGroup.ValidationAdd.class}) @RequestBody UserRegisterReqDto userRegisterReqDto
    ) {

        //1.注册
        UserVo userVo = loginService.register(userRegisterReqDto);

        //2.返回结果
        Map<String, Object> data = new HashMap<>(4);
        data.put("userInfo", userVo);
        return ResultVo.ok(data);
    }

    /**
     * @Author: zhangyong
     * description: (2) 修改用户注册信息
     * @Date: xxxx-05-19 9:39
     * @Param:
     * @Return:
     */
    @ApiOperation("修改用户注册信息")
    @PostMapping(value="updateRegister",produces = { "application/json"})
    public ResultVo<Map<String, Object>> updateRegister(
            @Validated @RequestBody UserRegisterReqDto userRegisterReqDto
    ) {

        //1.更新注册信息
        UserVo userVo = loginService.updateRegister(userRegisterReqDto);

        //2.返回结果
        Map<String, Object> data = new HashMap<>(4);
        data.put("userInfo", userVo);
        return ResultVo.ok(data);
    }


    @ApiOperation("通过用户ID查询用户注册信息")
    @ApiImplicitParam(name = "id", value = "用户id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getRegisterByUserId/{userId}")
    public ResultVo<Map<String, Object>> getRegisterByUserId(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @PathVariable Long userId
    ){
        return ResultVo.ok(userService.getRegisterByUserId(userInfo,userId));
    }


    /**
     * @Author: zhangyong
     * description: (3) 查询用户名是否存在
     * @Date: xxxx-05-19 9:39
     * @Param:
     * @Return:
     */
    @ApiOperation("查询用户名是否存在")
    @PostMapping(value="queryUsernameExist",produces = { "application/json"})
    public ResultVo<Map<String, Object>> queryUsernameExist(
            @Validated @RequestBody UsernameReqDto usernameReqDto
    ){
        return ResultVo.ok(loginService.queryUsernameExist(usernameReqDto.getUsername()));
    }

    /**
     * @Author 张勇
     * @Description //(3) 更新用户注册状态(暂时不用，因为目前注册后成功后直接成为正式用户,不需要审核)
     * @Date xxxx/05/21 18:43
     * @Param
     * @return
     */
    @Log(businessType = BusinessType.UPDATE)
    @ApiOperation("更新用户状态(正式/申请中/未通过审核)")
    @PostMapping(value = "updateUserRegType", produces = {"application/json"})
    @RequiresRoles(value = {"SuperAdmin","Admin"},logical = Logical.OR)
    public ResultVo<Map<String, Object>> updateUserRegType(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @Validated @RequestBody UserRegTypeReqDto userRegTypeReqDto
    ){
        return ResultVo.ok(userService.updateUserRegType(userInfo,userRegTypeReqDto));
    }

    /**
     * @Author: zhangyong
     * description: (7) 修改密码(重置密码)
     * @Date: xxxx-06-03 20:15
     * @Param:
     * @Return:
     */
    @ApiOperation("修改密码(重置密码)")
    @PostMapping(value = "updatePassword", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updatePassword(
            @Validated @RequestBody UserPwdUpdateDto userPasswordDto
    ){
        return ResultVo.ok(userService.updatePassword(userPasswordDto));
    }


   /**
    * @Author wangchao
    * @Description  通过角色code查询账号列表
    * @Date 2022/12/5 14:26 
    * @Param  
    * @return  
    */
    @ApiOperation("通过角色code查询用户")
    @GetMapping("/getUserListByRoleKey/{roleKey}")
    public ResultVo<Map<String, Object>> getUserListByRoleKey(
            @ApiIgnore @RequestHeader("userInfo") String userInfo,
            @PathVariable String roleKey
    ){
        return ResultVo.ok(userService.getUserListByRoleKey(userInfo,roleKey));
    }
}