#!/bin/sh
#如果要使用图形验证码，需要在openresty的容器内部安装基本的环境
#1.安装unzip，gcc automake autoconf libtool make，编译安装luarocks,
docker exec -it openresty /bin/bash -c "apt-get update; apt-get install -y unzip; apt-get install -y gcc automake autoconf libtool make; cd /usr/local/openresty/nginx/lua/checkcode/luarocks-3.11.0; ./configure; make && make install; cp /usr/local/openresty/nginx/lua/checkcode/sofile/* /usr/lib/x86_64-linux-gnu/"

#2.复制验证码lua脚本文件到容器内部指定位置
docker cp ./lua/checkcode/cmcheckcode.lua openresty:/usr/local/openresty/lualib/
docker cp ./lua/checkcode/gd.so openresty:/usr/local/openresty/lualib/

#3.重启
docker restart openresty

