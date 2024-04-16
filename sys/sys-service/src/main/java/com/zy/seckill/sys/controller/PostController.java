package com.zy.seckill.sys.controller;

import com.zy.seckill.common.annotation.Log;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.common.bo.vo.ResultVo;
import com.zy.seckill.common.enums.BusinessType;
import com.zy.seckill.sys.bo.dto.PostPageReqDto;
import com.zy.seckill.sys.bo.dto.PostReqDto;
import com.zy.seckill.sys.service.PostService;
import com.zy.seckill.common.valid.ValidationGroup.ValidationUpdate;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import springfox.documentation.annotations.ApiIgnore;
import org.springframework.validation.annotation.Validated;

import lombok.RequiredArgsConstructor;

import java.util.Map;



/*
 * @Author zhangyong
 * @Description PostController类
 * @Date 2022/02/21 15:03
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/sys/post")
@Api(tags = "职位")
@Validated
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    /*
     * @Author zhangyong
     * @Description //(1) 添加职位信息
     * @Date 2022/02/21 15:03
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.INSERT)
    @ApiOperation("添加职位信息")
    @PostMapping(value = "addPost", produces = { "application/json" })
    public ResultVo<Map<String, Object>> addPost(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated @RequestBody PostReqDto postReqDto
    ){
        return ResultVo.ok(postService.addPost(headerMap,postReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(2) 通过id删除职位信息
     * @Date 2022/02/21 15:03
     * @Param
     * @return
     **/
    @Log(businessType = BusinessType.DELETE)
    @ApiOperation("通过id删除职位信息")
    @ApiImplicitParam(name = "id", value = "职位id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/deletePostById/{id}")
    public ResultVo<Map<String, Object>> deletePostById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable Long id
    ){
         return ResultVo.ok(postService.deletePostById(headerMap,id));
    }

    /*
     * @Author zhangyong
     * @Description //(3) 更新职位信息
     * @Date 2022/02/21 15:03
     * @Param
     * @return
     **/
    @ApiOperation("更新职位信息")
    @Log(businessType = BusinessType.UPDATE)
    @PostMapping(value = "updatePost", produces = {"application/json"})
    public ResultVo<Map<String, Object>> updatePost(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated({ValidationUpdate.class}) @RequestBody PostReqDto postReqDto
    ){
        return ResultVo.ok(postService.updatePost(headerMap,postReqDto));
    }

    /*
     * @Author zhangyong
     * @Description //(4) 通过id查询职位信息
     * @Date 2022/02/21 15:03
     * @Param
     * @return
     **/
    @ApiOperation("通过id查询职位信息")
    @ApiImplicitParam(name = "id", value = "职位id", example = "133", dataType = "Long", paramType = "form")
    @GetMapping("/getPostById/{id}")
    public ResultVo<Map<String, Object>> getPostById(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @PathVariable Long id
    ){
        return ResultVo.ok(postService.getPostById(headerMap,id));
    }

    /*
     * @Author zhangyong
     * @Description //(5) 查询所有的职位信息列表并分页(支持关键字查询)
     * @Date 2022/02/21 15:03
     * @Param
     * @return
     **/
    @ApiOperation("查询所有的职位信息列表并分页(支持关键字查询)")
    @PostMapping(value = "getPostListPageVo", produces = { "application/json" })
    public ResultVo<PageVo<Map<String, Object>>> getPostListPageVo(
            @ApiIgnore @RequestHeader Map<String, Object> headerMap,
            @Validated @RequestBody PostPageReqDto postPageReqDto
    ){
        return ResultVo.ok(postService.getPostListPageVo(headerMap,postPageReqDto));
    }
}