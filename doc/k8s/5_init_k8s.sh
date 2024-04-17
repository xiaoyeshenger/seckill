#!/bin/bash

#(1).初始化k8s集群
kubeadm init --image-repository registry.aliyuncs.com/google_containers --kubernetes-version v1.16.0 --apiserver-advertise-address 10.0.24.8  --pod-network-cidr=10.244.0.0/16 --token-ttl 0
#如果需要公网访问k8s则加入公网IP
#kubeadm init --image-repository registry.aliyuncs.com/google_containers --kubernetes-version v1.16.0 --apiserver-cert-extra-sans=119.91.39.134 --apiserver-advertise-address 10.0.24.8  --pod-network-cidr=10.244.0.0/16 --token-ttl 0

#(2).创建k8s所需目录并授权
mkdir -p $HOME/.kube
cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
chown $(id -u):$(id -g) $HOME/.kube/config
export KUBECONFIG=$HOME/.kube/config

#(3).允许master节点运行pod，默认不能在master节点运行，一般选择开启，开启命令如下
kubectl taint nodes --all node-role.kubernetes.io/master-
