#!/bin/bash

#(1)关闭防火墙
echo "start ... init enveriment ...."
systemctl disable firewalld
systemctl stop firewalld

#(2)关闭selinux
setenforce 0
#永久关闭
sed -i "s/SELINUX=enforcing/SELINUX=disabled/" /etc/sysconfig/selinux
sed -i "s/SELINUX=enforcing/SELINUX=disabled/g" /etc/selinux/config

#(3)禁用swap
swapoff -a
#永久禁止
sed -i "s/.*swap.*/#&/" /etc/fstab

#(4)修改内核参数(配置转发参数,RHEL/CentOS7上由于iptables被绕过而导致流量路由不正确的问题，需要将net.bridge.bridge-nf-call-iptables在sysctl配置中设置为1)
cat <<EOF > /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF

#(5)设置ipv4转发
echo 1 > /proc/sys/net/ipv4/ip_forward

#(6)重新加载配置文件
sysctl --system

#(7)配置阿里云 k8s源
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=kubernetes
baseurl=https://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64/
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg https://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF

#(8)更新下载源缓存
yum clean all -y && yum makecache -y && yum repolist -y



