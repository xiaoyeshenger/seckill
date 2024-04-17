#!/bin/bash

#(1).在resolv.conf文件的最后写入如下dns服务器地址
sed -i '$a\nameserver 8.8.8.8' /etc/resolv.conf 
sed -i '$a\nameserver 4.4.4.4' /etc/resolv.conf 

