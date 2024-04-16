#!/bin/sh 
echo "======== delete mgr node and data......  ========"  
docker rm -f mysqlmgr01 mysqlmgr02 mysqlmgr03
rm -rf mysqlmgr01/data mysqlmgr01/log mysqlmgr01/mysql-files mysqlmgr02/data mysqlmgr02/log mysqlmgr02/mysql-files mysqlmgr03/data mysqlmgr03/log mysqlmgr03/mysql-files
