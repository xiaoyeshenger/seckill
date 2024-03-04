package com.jsxa.vapp.common.service;

public interface AccessService {

    //获取文件完整访问地址
    String getAccessUrl(String url,String folderName);

    //获取文件访问相对地址
    String getRelativeUrl(String url,String folderName);
}
