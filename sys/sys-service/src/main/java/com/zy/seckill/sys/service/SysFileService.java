package com.zy.seckill.sys.service;

import com.zy.seckill.common.bo.po.SysFile;
import com.zy.seckill.common.bo.vo.PageVo;
import com.zy.seckill.sys.bo.dto.SysFileExcelExportReqDto;
import com.zy.seckill.sys.bo.dto.SysFileReqDto;
import com.zy.seckill.sys.bo.dto.SysFilePageReqDto;

import java.util.Map;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @Author zhangyong
 * @Description //SysFileService接口
 * @Date 2022/07/29 11:14
 * @Param
 * @return
 **/
public interface SysFileService {


    //添加系统文件
    Map<String, Object> addSysFile(String userInfo,SysFileReqDto sysFileReqDto,MultipartHttpServletRequest request);

    //通过id删除系统文件
    Map<String, Object> deleteSysFileById(String userInfo,Long id);

    //更新系统文件
    Map<String, Object> updateSysFile(String userInfo,SysFileReqDto sysFileReqDto,MultipartHttpServletRequest request);

    //新增或更新系统文件
    Map<String, Object> insertOrUpdateSysFile(SysFileReqDto sysFileReqDto);

    //通过id查询系统文件
    Map<String, Object> getSysFileById(String userInfo,Long id);

    //查询所有系统文件列表并分页
    PageVo<Map<String, Object>> getSysFileListPageVo(String userInfo,SysFilePageReqDto sysFilePageReqDto);

    //通过file查询系统文件
    SysFile getSysFileByFileKey(String fileKey);

    //下载标准上传模板
    void downloadTemplateExcel(String userInfo,HttpServletResponse response);

    //通过excel导入系统文件
    Map<String, Object> importByExcel(String userInfo,MultipartHttpServletRequest request);

    //导出系统文件到excel(easyExcel方式)
    void exportToExcel(String userInfo,SysFileExcelExportReqDto sysFileExcelExportReqDto,HttpServletResponse response);
}