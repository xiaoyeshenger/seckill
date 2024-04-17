#!/bin/bash
source ../../../config
#1.如果没有安装docker，执行安装
function checkdocker(){
    $(which docker) >> /dev/null 2>&1
    echo $?
}
dnum=$(checkdocker)
#echo "是否安装docker的结果是--》$dnum"
if [ $dnum -ne 0 ]; then
    #安装wget
    yum install -y wget
    #备份原有的yum源并下载替换为阿里云
    mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
    wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
    #安装yum工具
    yum -y install yum-utils
    #添加docker 阿里云下载源
    yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    #安装最新版本的docker
    yum -y install docker-ce
    #设置开机启动docker
    systemctl enable docker
    #重启docker
    service docker restart
    echo '#####finish install docker#####'
fi
#2.如果没有安装指定名称的网桥，执行安装
#function checkbridge(){
#    echo $(docker network ls | awk  '{print $2}' | grep ${docker_bridge})
#}
#if [ -z "$(checkbridge)" ]; then
#    docker network create ${docker_bridge}
#    echo '#####finish create net bridge#####'
#fi

