#!/bin/sh
#重启后的mgr几点需要执行一次 START GROUP_REPLICATION; 才能重新加入集群
n=3
docker exec -it mysqlmgr0$n  env LANG=C.UTF-8  mysql -h localhost -uroot -pjskjmy666999 \
 -e "START GROUP_REPLICATION;" \
 -e "SELECT * FROM performance_schema.replication_group_members;"

