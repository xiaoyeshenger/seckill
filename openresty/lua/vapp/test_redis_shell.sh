#!/bin/bash

# 连接Redis服务器
key='RuntimeVaccineStock'
value=$(redis-cli -h 192.168.2.241 -p 6379 -a root -n 5 get ${key})
echo ${value}

