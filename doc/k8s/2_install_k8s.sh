#!/bin/bash

#(1).安装kubelet kubeadm kubectl
yum install -y kubelet-1.16.0 kubeadm-1.16.0 kubectl-1.16.0

#(2).设置开机启动并启动k8s
systemctl enable kubelet
systemctl start kubelet

