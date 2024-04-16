#!/bin/sh
#在mysql(MGR)中添加cannal 从节点账户以便监听binlog,如果使用proxysql或mycat等代理中间件则需要在中间件中也添加对应的账户
docker exec -it mysqlmgr01  env LANG=C.UTF-8  mysql -h localhost -uroot -pjskjmy666999 \
  -e "show master status;" \
  -e "create user  IF NOT EXISTS 'canal'@'%' identified WITH mysql_native_password by 'canal888555';" \
  -e "GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO canal@'%';" \
  -e "flush privileges;" \
  -e "SELECT * FROM mysql.user;"
