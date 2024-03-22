#!/bin/sh
#如果要使用jwt验证toekn，需要在openresty的容器内部安装基本的环境,先安装openresty的包管理工具openresty-opm，再利用opm安装jwt
docker exec -it openresty /bin/bash -c "apt-get update; apt-get install -y openresty-opm; opm get SkyLothar/lua-resty-jwt"

