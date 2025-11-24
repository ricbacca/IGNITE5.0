STARTUP:

1. Installare OVS su Raspberry: https://snapcraft.io/install/openvswitch/raspbian
2. Eseguire prima su SW1 (ITBOUNDARY) e poi su SW2 (OTBOUNDARY) lo script preparato, che esegue:
   - Avvio di SSH e configurazione con usr e pwd standard
   - Avvio di switch open flow e relativa configurazione delle porte e del controller esterno
   - I Controller esterni sono all'IP 192.168.10.2 rispettivamente: porta 6633 per SW1 e 6653 per SW2	