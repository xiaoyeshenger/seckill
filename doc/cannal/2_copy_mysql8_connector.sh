#!/bin/sh
#如果mysql使用的是8版本，则将mysql8的驱动拷贝到lib目录
chmod -R 777 ./mysql-connector-java-8.0.22.jar
chmod +st ./mysql-connector-java-8.0.22.jar
docker cp ./mysql-connector-java-8.0.22.jar canal-server:/home/admin/canal-server/lib/

#删除原来的mysql5驱动
docker exec -it canal-server rm -rf /home/admin/canal-server/lib/mysql-connector-java-5.1.48.jar
