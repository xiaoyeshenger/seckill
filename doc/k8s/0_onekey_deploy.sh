#!/bin/sh 
#1.初始化安装环境
echo "@======== step1 ---> env-init ========@"
sh ./1_env-init.sh

#2.安装k8s
echo "@======== step2 ---> install_k8s ========@"
sh ./2_install_k8s.sh

#3.设置域名服务器
echo "@======== step3 ---> set_resolv_conf ========@"
sh ./3_set_resolv_conf.sh

#4.配置docker cgroup
echo "@======== step4 ---> set_docker_cgroup ========@"
sh ./4_set_docker_cgroup.sh

#5.初始化k8s集群，会产生加入集群的token。后续其他子节点即可根据该token加入到集群
echo "@======== step5 ---> init_k8s ========@"
sh ./5_init_k8s.sh

#6.安装网络插件flannel，目的是各个服务之间可以相互通信
echo "@======== step6 ---> install_flannel ========@"
sh ./6_install_flannel.sh

#7.安装ingress-nginx，以便统一入口转发请求到后端服务上，并且ingress具备修改配置后自动刷新到nginx的功能，所以可以不重启使得nginx生效
echo "@======== step7 ---> install_ingress ========@"
sh ./7_install_ingress.sh
