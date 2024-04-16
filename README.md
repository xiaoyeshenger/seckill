# seckill 高并发秒杀系统——与京东淘宝同样架构的高并发系统

#### 介绍
基于Openresty+ Lua脚本的高并发高性能商品秒杀系统，tps可高达10W并发

#### 软件架构
1.软件架构说明
软件架构和京东、淘宝的秒杀系统原理一致，其中参考书籍 京东首席架构师张开涛著作的《亿级流量网站架构核心技术——跟开涛学搭建高可用高并发系统》、阿里P9著作的《阿里巴巴的高并发系统，系统架构如下:
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


#### 本系统能够支撑高并发的原因，以下列举其中的一些要点:
1.在Openresty(Nginx)层面
(1).Nginx实现流量限制，防止机器作弊
    通过使用Nginx的limit_req_zone模块，实现流量限制为rate=3r/s，即每个客户端IP只能在1秒内请求不超过3次

(2).Nginx实现图形验证码，实现流量错峰
    通过使用Nginx + lua脚本 + gd.so( c图形库) 生成4位数字和字母结合的图形验证码，客户端必须提交正确的图形验证码才能进行下一步请求，由于客户端输入验证码的时间不一致，所以实现了流量的错峰，减少系统的压力

(3).Nginx实现接口的Token验证和参数校验
   通过使用Nginx + lua脚本+ jwt模块，在Nginx完成token校验和请求参数的校验，校验通过再往后端SpringBoot转发请求，不满足要求的请求直接返回统一的json响应结构，大大提高性能

(4).Nginx本地内存实现库存更新和库存感知
   通过使用Nginx + lua脚本在Ningx启动时获取redis中的库存数量并保存到Nginx本地内存，当疫苗预约接口请求过来时，首先在本地内存判断是否有库存，有的话直接减1，不用再查找redis判断库存，实现极致性能

2.SpringCloud层面
(1).gateway + sentinel实现限流
  在Nginx做了限流后，在系统的网关层利用SpringCloud Gateway + Sentinel也做了同样的限流，每个客户端IP只能在1秒内请求不超过3次

(2).库存的预扣减
  为了保证系统的高性能，首先通过操作redis实现库存的预扣减，避免直接操作Mysql

(3).库存不超卖
    库存扣减的核心是查询和扣减2步必须是原子操作，为了应对高并发使用redis+lua脚本实现库存的原子性无锁扣减以防止超卖，性能大于分布式锁

(4).系统的削峰解耦
   库存预扣减成功后通过使用消息队列RocketMQ发送订单信息到指定的topic实现削峰解耦，订单消费者监听到订单消息后在Mysql中实现真正的库存扣减，并且使用乐观锁(加version版本号的方式)再次防止库存超卖，即在减库存前判断库存是否大于0，并且加上当前线程查询库存时的版本号来更新库存

(5).分布式事务
   由于系统采用SpringCloud微服务开发，疫苗库存和预约订单是两个独立的微服务，而订单生成和库存扣减必须保证事务性，所以就涉及到分布式事务的问题。出于便利性和系统实际情况的考虑，选择使用Seata的AT模式保证分布式事务

(6).数据一致性
   通过使用阿里巴巴的Canal组件监听Mysql的binlog变化，同步Mysql和redis以及jvm内存的数据，保证数据的一致性

(7).库存扣减数据
   因为存在着Redis宕机和java服务同时宕机的可能，这样会造成数据丢失，所以需要在redis预扣减成功后立马持久化保存扣减记录，将扣减记录保存到本地RocksDB数据库，这样就实现了简单的WAL机制，便于后期查错和同步数据

(8).等等。。。


#### 安装教程

一.环境部署

1.安装Mysql MGR集群

sh doc/mysql/0_onekey_deploy.sh 通过docker-compose一键部署Mysql MGR集群实现主从复制


2.安装Proxysql中间件

sh doc/proxysql/0_onekey_deploy.sh 通过docker-compose一键部署proxysql中间件实现读写分离


3.安装rocketmq

sh doc/rocketmq/1_run_compose.sh.sh 通过docker-compose一键部署rocketmq


4.安装canal

sh doc/canal/1_run_compose.sh.sh 通过docker-compose一键部署cannal


5.安装nacos

sh doc/nacos/1_run_compose.sh.sh 通过docker-compose一键部署nacos


6.安装openresty(nginx)

sh doc/openresty/1_run_compose.sh.sh.sh 通过docker-compose一键部署openresty

7.安装redis和mongo

二.创建数据库
1.每个微服务对应自己的数据库，所以需要安装5个数据库 seckill_inventory、seckill_order、seckill_sys、seckill_task_center、seckill_seata
2.执行 doc/sql目录下的sql文件
3.在nacos中修改每个微服务对应的数据库连接地址

#### 使用说明

1.运行gateway、inventory、order、sys、ask_center5个微服务
2.使用postman测试接口是否通畅
3.使用jmeter或ab对秒杀接口进行并发测试
4.结果显示并发能力可达xx万/s

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 参与讨论

1.欢迎各位朋友参与讨论

2.如需详细了解或帮助，可加我微信 ZZ449557260
![输入图片说明](https://foruda.gitee.com/images/1713264181441085014/54588ab1_8850735.jpeg "wei_xin.jpg")

