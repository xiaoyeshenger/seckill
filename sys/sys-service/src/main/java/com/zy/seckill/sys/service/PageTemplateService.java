package com.zy.seckill.sys.service;

import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.PageTemplateReqDto;
import com.zy.seckill.sys.bo.dto.PageTemplatePageReqDto;

import java.util.Map;

import com.zy.seckill.sys.bo.po.PageTemplate;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/*
 * @Author zhangyong
 * @Description //PageTemplateService接口
 * @Date 2022/04/26 11:24
 * @Param
 * @return
 **/
public interface PageTemplateService {


    //添加页面模板
    Map<String, Object> addPageTemplate(PageTemplateReqDto pageTemplateReqDto,MultipartHttpServletRequest request);

    //通过id删除页面模板
    Map<String, Object> deletePageTemplateById(Long id);

    //更新页面模板
    Map<String, Object> updatePageTemplate(PageTemplateReqDto pageTemplateReqDto,MultipartHttpServletRequest request);

    //通过id查询页面模板
    Map<String, Object> getPageTemplateById(Long id);

    //查询所有页面模板列表并分页
    PageVo<Map<String, Object>> getPageTemplateListPageVo(PageTemplatePageReqDto pageTemplatePageReqDto);

    //通过id启用页面模板
    Map<String, Object> enablePageTemplateById(Long id);


    //通过parkId查询生效的页面模板
    PageTemplate getPageTemplateByParkId(Long parkId);


    //通过parkId查询生效的页面模板
    Map<String, Object> queryPageTemplateByParkId(Long id);
}