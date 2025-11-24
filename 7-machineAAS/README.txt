ESEGUBIILE ANCHE SU ARCHITETTURA ARM RASPBERRY/REV_PI

STARTUP:

1. Docker build -t machine_aas .
2. docker run --rm -e ASSET_IP=192.168.1.10:8080 -e AAS_IP=10.0.0.5:443 -e ID=Machine1 -e REGISTRY_IP=localhost:4000 machine_aas

NOTA:
- Avviare MachineAAS solo DOPO aver avviato il Registry. Senza il Registry avviato, non partirà!
- aprire le porte, che l'AAS utilizza (le stesse di AAS_IP) e che l'ASSET utilizza (le stesse di ASSET_IP)
- REGISTRY IP su porta 4000 di backend sempre !!


ESEMPIO

docker run --rm \
-e ASSET_IP=192.168.1.10:8080 \
-e AAS_IP=localhost:6001 \
-e ID=MachineOne \
-e MQTT_IP=172.17.0.2 \
-e REGISTRY_IP=172.17.0.3:4000 \
-p 6001:6001 \
-p 8080:8080 machine_aas

NOTA BENE:

Per trovare IP degli altri container utilizzati, usare il comando "docker inspect <<nome_container>>"


Il Nome container è ottenibile con "docker container ls"