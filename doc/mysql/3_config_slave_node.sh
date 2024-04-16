#!/bin/sh
#等待mysqlmg01节点启动，启动成功后再执行主节点配置命令
for N in {2..3}
do
 echo  ====mysqlmgr0$N node is starting,please wait a moment...====
 while [ $(docker logs --tail=1 mysqlmgr0$N 2>&1 | grep "ready for connections" | wc -l) -eq 0 ]
  do
    echo -n '.'
    sleep 2
  done
 echo  ====mysqlmgr0$N node started, execute configuration...====
 docker exec -it mysqlmgr0$N  env LANG=C.UTF-8  mysql -h localhost -uroot -pjskjmy666999 \
   -e "SET SQL_LOG_BIN=0;" \
   -e "create user  IF NOT EXISTS 'repl_user'@'%' identified WITH mysql_native_password by 'my999666';" \
   -e "GRANT REPLICATION SLAVE ON *.* TO repl_user@'%';" \
   -e "flush privileges;" \
   -e "SET SQL_LOG_BIN=1;" \
   -e "change master to master_user='repl_user', master_password='my999666' for channel 'group_replication_recovery';" \
   -e "reset master;" \
   -e "START GROUP_REPLICATION;" \
   -e "SELECT * FROM performance_schema.replication_group_members;"
done

