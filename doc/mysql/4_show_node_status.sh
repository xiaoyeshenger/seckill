for N in 3
do docker exec -it mysqlmgr0$N  env LANG=C.UTF-8  mysql -h localhost -uroot -pjskjmy666999 \
  -e "SELECT * FROM performance_schema.replication_group_members;"
done
