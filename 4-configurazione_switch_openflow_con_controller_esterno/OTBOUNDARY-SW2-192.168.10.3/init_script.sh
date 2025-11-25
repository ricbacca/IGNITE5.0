#!/bin/sh

cp /ssh/sshd_config /etc/ssh/sshd_config
echo -e "root:111111" | chpasswd
service ssh start
Tail -f /dev/null

service openvswitch-switch start

ovs-vsctl add-br sw2
ovs-vsctl set Bridge sw2 other-config:datapath-id=0000000000000002

ovs-vsctl add-port sw2 eth0
ovs-vsctl add-port sw2 eth1

ovs-vsctl set-controller sw2 tcp:192.168.10.2:6653

ovs-vsctl show


