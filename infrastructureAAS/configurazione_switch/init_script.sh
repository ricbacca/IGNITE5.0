#!/bin/sh

cp /ssh/sshd_config /etc/ssh/sshd_config
echo -e "root:111111" | chpasswd
service ssh start
Tail -f /dev/null
