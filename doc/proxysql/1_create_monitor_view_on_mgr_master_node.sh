#!/bin/sh
#将create_monitor_view.sql 拷贝到mgr 主节点的/home/下
n=1
docker cp ./create_monitor_view.sql mysqlmgr0${n}:/home/
#在mgr 主节点中创建视图(vies)，这是proxysql官方规定的，需要这个才能监控集群状态
docker exec -it mysqlmgr00${n} env LANG=C.UTF-8  mysql -h localhost -uroot -pjskjmy666999 \
  -e "source /home/create_monitor_view.sql;" \
  -e "select * from gr_member_routing_candidate_status;"
