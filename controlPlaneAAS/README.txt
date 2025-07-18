ESEGUBIILE ANCHE SU ARCHITETTURA ARM RASPBERRY/REV_PI

STARTUP:

1. Docker build -t controlplane_aas .
2. docker run --rm \
   -e AAS_IP=localhost:6002 \
   -e CNT1_IP=192.168.1.10 \
   -e CNT2_IP=192.168.1.11 \
   -e REGISTRY_IP=172.17.0.2:4000 \
   -p 6002:6002 controlplane_aas

NOTA:
- Avviare ControlPlaneAAS solo DOPO aver avviato il Registry e i controller. Senza non partirà!
- aprire le porte, che l'AAS utilizza (le stesse di AAS_IP)
- REGISTRY IP su porta 4000 di backend sempre !!
- i due controller a cui connettersi sono semplicemente due PC/RASPBERRY. Vedi "configurazione_controller"