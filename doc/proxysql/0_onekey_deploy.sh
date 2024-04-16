#!/bin/sh 
echo "@======== step1 ---> create monitor view on the MGR master  ========@"
sh ./1_create_monitor_view_on_mgr_master_node.sh

echo "@======== step2 ---> create proxysql container ========@"
sh ./2_run_compose.sh

echo "@======== step3 ---> create_proxysql_monitor_user  ========@"
sh ./3_create_proxysql_monitor_user.sh

echo "@======== step4 ---> show mgr status ========@"
sh ./4_show_mgr_status.sh
