package com.jsxa.vapp.sys.service;

import com.jsxa.vapp.common.bo.vo.PageVo;
import com.jsxa.vapp.sys.bo.dto.DeptReqDto;
import com.jsxa.vapp.sys.bo.dto.DeptPageReqDto;
import java.util.Map;

/*
 * @Author zhangyong
 * @Description //DeptService接口
 * @Date 2022/02/21 15:06
 * @Param
 * @return
 **/
public interface DeptService {


    //添加部门
    Map<String, Object> addDept(Map<String, Object> headerMap,DeptReqDto deptReqDto);

    //通过id删除部门
    Map<String, Object> deleteDeptById(Map<String, Object> headerMap,Long id);

    //更新部门
    Map<String, Object> updateDept(Map<String, Object> headerMap,DeptReqDto deptReqDto);

    //通过id查询部门
    Map<String, Object> getDeptById(Map<String, Object> headerMap,Long id);

    //查询所有部门列表并分页
    PageVo<Map<String, Object>> getDeptListPageVo(Map<String, Object> headerMap,DeptPageReqDto deptPageReqDto);
}