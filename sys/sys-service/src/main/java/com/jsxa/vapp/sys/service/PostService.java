package com.jsxa.vapp.sys.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.sys.bo.dto.PostReqDto;
import com.jsxa.vapp.sys.bo.dto.PostPageReqDto;
import java.util.Map;

/*
 * @Author zhangyong
 * @Description //PostService接口
 * @Date 2022/02/21 15:03
 * @Param
 * @return
 **/
public interface PostService {


    //添加职位
    Map<String, Object> addPost(Map<String, Object> headerMap,PostReqDto postReqDto);

    //通过id删除职位
    Map<String, Object> deletePostById(Map<String, Object> headerMap,Long id);

    //更新职位
    Map<String, Object> updatePost(Map<String, Object> headerMap,PostReqDto postReqDto);

    //通过id查询职位
    Map<String, Object> getPostById(Map<String, Object> headerMap,Long id);

    //查询所有职位列表并分页
    PageVo<Map<String, Object>> getPostListPageVo(Map<String, Object> headerMap,PostPageReqDto postPageReqDto);
}