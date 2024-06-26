# seckill 基于K8s+Docker+Openresty+Lua+SpringCloudAlibaba的高并发秒杀系统——与京东淘宝同样架构

#### 介绍
  基于K8s+Docker+Openresty+Lua+SpringCloudAlibaba的高并发高性能商品秒杀系统，本系统实测单台(16核 32G 主频2.2GHz)openresty(nginx) 的QPS可高达6w并发，如果您需要应对100w的并发，则需要100w/6w=17台openresty服务器，17台服务器怎么同时接收并处理这100w的高并发流量呢？当然是商业CDN，您需要结合实际业务考虑是否购买CDN服务，阿里、腾讯、华为、亚马逊均有该服务。


#### 软件架构
1.软件架构说明

  软件架构和京东、淘宝的秒杀系统架构一致，其中参考书籍 京东首席架构师张开涛著作的《亿级流量网站架构核心技术——跟开涛学搭建高可用高并发系统》、阿里P9著作的《亿级并发系统架构设计》，系统架构如下:

![输入图片说明](https://foruda.gitee.com/images/1713264245530081241/eceea6c2_8850735.jpeg "arc_dig.jpg")

2.技术栈

开发环境：IDEA、JDK1.8、Maven、Gitlab

软件架构：Nginx+SpringCloud + Vue + MyBatis3 Dynamic Sql

Web框架：SpringCloudAlibaba 微服务框架

Orm框架：MyBatis3 Dynamic Sql

数据库：Mysql、Redis、MongoDB、RocksDB

分库分表：Sharding-Jdbc

权限控制：Shiro+Jwt

限流组件：Sentinel

实时数据传输：WebSocket

分布式定时：XXX-JOB

分布式事务：Seata

消息队列：RocketMQ

同步组件：Canal

对象存储：Minio

在线文档：Swagger

短信推送：极光推送

反向代理：Nginx (OpenResty)

部署环境：Docker

集群管理：K8s

运行环境：Centos 7.4


#### 本系统支撑高并发的原因:

1.由于SpringCloud(Springboot)内置的tomcat服务器并发能力有限(实际生产中单台500左右)，而Nginx是高性能的web服务器(理论单台的最高并发可达10w/s，实际生产中优化得好可达6~7w)，所以将大部分流量在Nginx层面进行处理，这样就能避免高并发大流量直接压垮SpringCloud(Springboot)。别忘了秒杀系统的特点是人多物少、读多写少，真正能够秒杀成功的用户有限(秒杀成功的用户数量=参与秒杀的商品数量如500)，所以大部分流量都需要在Nginx层进行过滤处理，而实际上让后端SpringCloud处理的业务请求数量其实仅仅等于参与秒杀的商品数量如500而已，所以没必要让流量直接打到SpringCloud。


2.由于Nginx支持Lua语言,而OpenResty则是基于Nginx集成Lua开发的WEB服务器，所以本系统的限流、流量错峰、token校验、业务参数校验、数据查询均通过Lua实现，这样便可实现极致的性能。


3.后端SpringCloud通过牺牲内存换取性能的方式，将热点数据提前加载到JVM内存如HashMap和Redis中，实现高性能，以便支撑高并发


4.系统中开展的一些实际工作:

1).在Openresty(Nginx)层面

(1).Nginx实现流量限制，防止机器作弊

   通过使用Nginx的limit_req_zone模块，实现流量限制为rate=3r/s，即每个客户端IP只能在1秒内请求不超过3次

(2).Nginx实现图形验证码，实现流量错峰

   通过使用Nginx + lua脚本 + gd.so( c图形库) 生成4位数字和字母结合的图形验证码，客户端必须提交正确的图形验证码才能进行下一步请求，由于客户端输入验证码的时间不一致，所以实现了流量的错峰，减少系统的压力

(3).Nginx实现接口的Token验证和参数校验

   通过使用Nginx + lua脚本+ jwt模块，在Nginx完成token校验和请求参数的校验，校验通过再往后端SpringBoot转发请求，不满足要求的请求直接返回统一的json响应结构，大大提高性能

(4).Nginx本地内存实现库存更新和库存感知

   通过使用Nginx + lua脚本在Ningx启动时获取redis中的库存数量并保存到Nginx本地内存，当页面秒杀接口请求过来时，首先在本地内存判断是否有库存，有的话直接减1，不用再查找redis判断库存，实现极致性能

2).SpringCloud层面

(1).gateway + sentinel实现限流

  在Nginx做了限流后，在系统的网关层利用SpringCloud Gateway + Sentinel也做了同样的限流，每个客户端IP只能在1秒内请求不超过3次

(2).库存的预扣减

  为了保证系统的高性能，首先通过操作redis实现库存的预扣减，避免直接操作Mysql

(3).库存不超卖

  库存扣减的核心是查询和扣减2步必须是原子操作，为了应对高并发使用redis+lua脚本实现库存的原子性无锁扣减以防止超卖，性能大于分布式锁

(4).系统的削峰解耦

   库存预扣减成功后通过使用消息队列RocketMQ发送订单信息到指定的topic实现削峰解耦，订单消费者监听到订单消息后在Mysql中实现真正的库存扣减，并且使用乐观锁(加version版本号的方式)再次防止库存超卖，即在减库存前判断库存是否大于0，并且加上当前线程查询库存时的版本号来更新库存

(5).分布式事务

   由于系统采用SpringCloud微服务开发，库存服务和秒杀服务是两个独立的微服务，而订单生成和库存扣减必须保证事务性，所以就涉及到分布式事务的问题。出于便利性和系统实际情况的考虑，选择使用Seata的AT模式保证分布式事务

(6).数据一致性

   通过使用阿里巴巴的Canal组件监听Mysql的binlog变化，同步Mysql和redis以及jvm内存的数据，保证数据的一致性

(7).库存扣减数据

   因为存在着Redis宕机和java服务同时宕机的可能，这样会造成数据丢失，所以需要在redis预扣减成功后立马持久化保存扣减记录，将扣减记录保存到本地RocksDB数据库，这样就实现了简单的WAL机制，便于后期查错和同步数据

(8).接口幂等性

   接口中传入提前请求到的随机token，系统采用先删除token后执行业务的方式保证接口幂等性

(9).等等。。。


#### 安装教程

一.环境部署


1.安装docker

sh doc/docker/0_onekey_deploy.sh 一键部署docker环境


2.安装k8s

sh doc/k8s/0_onekey_deploy.sh 一键部署k8s实现docker容器的集群管理


3.安装openresty(nginx)

sh doc/openresty/1_run_compose.sh.sh.sh 通过docker-compose一键部署openresty


4.安装nacos

sh doc/nacos/1_run_compose.sh.sh 通过docker-compose一键部署nacos，安装按完成后，将配置文件doc/nacos/nacos_config_export.zip导入到nacos


5.安装mysql mgr集群

sh doc/mysql/0_onekey_deploy.sh 通过docker-compose一键部署Mysql MGR集群实现主从复制


6.安装proxysql中间件

sh doc/proxysql/0_onekey_deploy.sh 通过docker-compose一键部署proxysql中间件实现读写分离


7.安装redis

sh doc/redis/1_run_compose.sh.sh 通过docker-compose一键部署redis


8.安装mongodb

sh doc/mongodb/1_run_compose.sh.sh 通过docker-compose一键部署mongodb


9.安装rocketmq

sh doc/rocketmq/1_run_compose.sh.sh 通过docker-compose一键部署rocketmq


10.安装canal

sh doc/canal/1_run_compose.sh.sh 通过docker-compose一键部署cannal



二.导入数据库

1.每个微服务对应自己的数据库，所以需要安装5个数据库 seckill_inventory、seckill_order、seckill_sys、seckill_task_center、seckill_seata

2.执行 doc/sql目录下的sql文件

3.在nacos中修改每个微服务对应的数据库连接地址


#### 使用说明

1.运行gateway、inventory、order、sys、task_center5个微服务

2.使用postman测试接口是否通畅

3.使用jmeter或ab对秒杀接口进行并发测试

4.结果显示并发能力可达**w/s


#### 参与贡献

1.黑白条纹

2.QinJ5

3.Zoe

在此特别谢鸣骄姐与Zoe的鼎力支持，感谢您们的一路支持!!


#### 参与讨论

1.欢迎各位朋友参与讨论

github地址： https://github.com/xiaoyeshenger/seckill

gitee地址： https://gitee.com/qj-zye/seckill


2.如需详细了解或帮助，可加我微信 ZZ449557260

![输入图片说明](https://foruda.gitee.com/images/1713264181441085014/54588ab1_8850735.jpeg "wei_xin.jpg")

