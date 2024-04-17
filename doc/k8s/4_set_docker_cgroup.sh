#!/bin/bash

#(1).设置docker的 cgroup
sed -i -e '{\
"registry-mirrors": [\
"https://kfwkfulq.mirror.aliyuncs.com",\
"https://2lqq34jg.mirror.aliyuncs.com",\
"https://pee6w651.mirror.aliyuncs.com",\
"https://registry.docker-cn.com",\
"http://hub-mirror.c.163.com"\
],\
"dns": ["114.114.114.114","8.8.4.4"],\
"exec-opts": ["native.cgroupdriver=systemd"]\
}' /etc/docker/daemon.json 

#(2).重启docker
systemctl restart docker

