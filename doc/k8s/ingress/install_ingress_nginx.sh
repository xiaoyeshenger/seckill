#!/bin/sh
#Ingress由Ingress规则、IngressController、IngressClass这3部分组成。Ingress资源只是一系列路由转发配置，必须使用IngressController才能让路由规则生效，而IngressClass是IngressController的具体实现,比如nginx,Traefik等
#ingres的作用是通过把配置文件抽象出来，配置文件就类似于Nginx的转发规则，通过ingress可以动态将配置信息刷新到nginx，并且让nginx执行reload自动加载配置信息，实现自动化管理Nginx，配置信息为ingress.yaml。可实现服务转发
#使用原则：先部署IngressController → 再部署Ingress资源

#(1).先给需要安装ingress的节点打上节点选择器标签，这样ingress才会在打了节点选择器标签的节点进行安装，标签由key: value构成，ingress的标签在ingress-nginx.yaml的316行中定义(nginx-ingress: "true")
kubectl label node vm-24-8-centos nginx-ingress=true

#(2).修改ingress-nginx.yaml 根据需要暴露的外网端口修改277行的30080的端口号，后期外网IP或域名:30080即可访问nginx并进行转发

#(3).安装ingress-nginx
kubectl delete -f ingress-nginx.yaml
kubectl apply -f ingress-nginx.yaml

#(4).查看基本信息
kubectl get namespace
kubectl get pods -n ingress-nginx

#(5).通过查看ingress的服务,查看到对外服务的ip，然后执行 curl ip  返回nginx的404代表ingress-nginx安装成功
kubectl get svc -n ingress-nginx

#(6).将ingress资源通过controller刷新到ingressClass,即配置信息自动刷新到nginx中，并且让nginx执行nginx -s reload实现无感刷新
kubectl apply -f ingress.yaml
