#!/bin/sh
docker exec -it proxysql mysql -h127.0.0.1 -P6032 -uradmin -pradmin --prompt "ProxySQL Admin>" \
  -e "select * from runtime_mysql_servers;" \
  -e "select hostgroup,schemaname,username,digest,digest_text,count_star from stats_mysql_query_digest;" \
   

