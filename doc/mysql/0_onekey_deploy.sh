#!/bin/sh 
echo "@======== step1 ---> create mgr container ========@"
sh ./1_run_compose.sh

echo "@======== step2 ---> config master node ========@"
sh ./2_config_master_node.sh

echo "@======== step3 ---> config slave node ========@"
sh ./3_config_slave_node.sh

echo "@======== step4 ---> show node status ========@"
sh ./4_show_node_status.sh
