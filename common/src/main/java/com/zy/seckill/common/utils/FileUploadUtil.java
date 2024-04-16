package com.zy.seckill.common.utils;

import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUploadUtil {

    /*
     * @Author zhangyong
     * @Description // 多个文件上传
     * @Date 上午 11:44 2019/8/14 0014
     * @Param [files, fileName]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public static Map<String, Object> uploadFileList(List<MultipartFile> fileList, String fileUploadPath, String fileName) throws IOException {

        //1.初始化map以封装返回信息
        Map<String, Object> info = new HashMap<>();

        //2.判断文件是否为空
        if (fileList.size() < 1) {
            info.put("msg", "file_empty");
            return info;
        }
        for (int i = 0; i < fileList.size(); ++i) {
            MultipartFile file = fileList.get(i);
            if (!file.isEmpty()) {
                String filename = file.getOriginalFilename();
                String type = filename.indexOf(".") != -1 ? filename.substring(filename.lastIndexOf("."), filename.length()) : null;
                if (type == null) {
                    info.put("msg", "file_empty");
                    return info;
                }
                /*if (!(".PNG".equals(type.toUpperCase()) || ".JPG".equals(type.toUpperCase()))) {
                    info.put("msg", "error_file_extension");
                    return info;
                }*/
            }
        }

        //String fileSeperator = File.separator;
        //3.循环上传文件
        for (int i = 0; i < fileList.size(); ++i) {

            //(1)拿到文件
            MultipartFile file = fileList.get(i);

            //(2)获取到文件名
            String fileSeperator = File.separator;//文件分隔符
            String originalFilename = file.getOriginalFilename(); // 原文件名
            String prefixName = fileName.substring(0, fileName.lastIndexOf("."));// 前缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."), fileName.length());//后缀名

            //(3)得到目标文件
            File dest = new File(fileUploadPath + fileName);
            if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                dest.getParentFile().mkdirs();//不存在则新建父目录
            }
            try {
                //(4).保存文件到本地磁盘；
                if (dest.exists()) {
                    dest.delete();
                }
                file.transferTo(dest);

            }catch (IllegalStateException e) {
                e.printStackTrace();
                info.put("msg", "false");
                return info;
            } catch (IOException e) {
                e.printStackTrace();
                info.put("msg", "false");
                return info;
            }

        }
        return info;
    }


    /*
     * @Author zhangyong
     * @Description // 单个个文件上传
     * @Date 上午 11:44 2019/8/14 0014
     * @Param [files, fileName]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Async
    public static Map<String, Object> uploadFile(MultipartFile file, String fileUploadPath,String fileName) throws IOException {

        //1.初始化map以封装返回信息
        Map<String, Object> info = new HashMap<>();

        //2.判断文件是否为空
        if (file.isEmpty()) {
            info.put("msg", "file_empty");
            return info;
        }
        /*if (!(".PNG".equals(type.toUpperCase()) || ".JPG".equals(type.toUpperCase()))) {
            info.put("msg", "error_file_extension");
            return info;
        }*/

        //3.得到目标文件
        File dest = new File(fileUploadPath + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdirs();//不存在则新建父目录
        }
        try {
            //(3).保存文件到本地磁盘,如果文件以及存在则先删除
            if (dest.exists()) {
                dest.delete();
            }
            FileOutputStream fo = new FileOutputStream(new File(fileUploadPath + fileName));
            InputStream in = file.getInputStream();
            IOUtils.copy(in, fo);

            fo.close();
            fo.close();

        }catch (IllegalStateException e) {
            e.printStackTrace();
            info.put("msg", "false");
            return info;
        } catch (IOException e) {
            e.printStackTrace();
            info.put("msg", "false");
            return info;
        }
        info.put("msg","上传成功");
        return info;
    }
}
