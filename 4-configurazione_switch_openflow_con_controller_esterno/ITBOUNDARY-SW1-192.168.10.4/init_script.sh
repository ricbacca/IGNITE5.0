#!/bin/sh

service openvswitch-switch start

ovs-vsctl add-br sw1
ovs-vsctl set Bridge sw1 other-config:datapath-id=0000000000000001

ovs-vsctl add-port sw1 eth0
ovs-vsctl add-port sw1 eth1

ovs-vsctl set-controller sw1 tcp:192.168.10.2:6633

ovs-vsctl show

cp /ssh/sshd_config /etc/ssh/sshd_config
echo -e "root:111111" | chpasswd
service ssh start


