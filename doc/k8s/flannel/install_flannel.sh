#!/bin/sh

#(1).拷贝 cni plugin(docker网络插件) 到指定目录,使得flannel能够正常工作
mkdir /opt/cni-plugins/
tar -zxvf ./cni-plugins-linux-amd64-v0.8.6.tgz -C /opt/cni-plugins/
cp /opt/cni-plugins/flannel /opt/cni/bin/

#(2).加载flannel的docker镜像
docker load < flanneld-v0.12.0-amd64.docker

#(3).安装flannel
kubectl apply -f kube-flannel.yml 
