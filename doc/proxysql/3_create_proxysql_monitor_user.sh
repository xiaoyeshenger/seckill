#!/bin/sh
docker exec -it mysqlmgr0$1  env LANG=C.UTF-8  mysql -h localhost -uroot -pjskjmy666999 \
  -e "create user  IF NOT EXISTS 'proxysql_monitor'@'%' identified WITH mysql_native_password by 'my555888';" \
  -e "GRANT ALL ON *.* TO 'monitor'@'%';" \
  -e "create user  IF NOT EXISTS 'proxysql_user'@'%' identified WITH mysql_native_password by 'my888555';" \
  -e "GRANT SELECT, INSERT, UPDATE, DELETE ON *.* TO  'proxysql_test'@'%';" \
  -e "flush privileges;" \
  -e "select user,host from mysql.user;"

